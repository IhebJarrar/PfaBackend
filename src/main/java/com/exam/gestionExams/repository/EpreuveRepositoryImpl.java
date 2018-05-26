package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Creneau;
import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.EpreuvesWithOtherAttribute;
import com.exam.gestionExams.model.Groupe;
import com.exam.gestionExams.model.LocalWithAffectation;
import com.exam.gestionExams.model.Parameters;
import com.exam.gestionExams.model.ResultEpreuveGroupe;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantWithOtherAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
public class EpreuveRepositoryImpl implements EpreuveRepositoryCustom {

	@Autowired
	EpreuveRepository epreuveRepository;
	@PersistenceContext
	EntityManager em;

	List<Epreuves> epreuves;

	@Override
	public void effacerEpreuveVide() {
		epreuves=epreuveRepository.findAll();
		for (Epreuves epreuve: epreuves)
		{
			if(epreuve.getNom().equals(""))
				epreuveRepository.delete(epreuve.getId());
		}
	}

	@Override
	public void createEpreuvesForAllGroupes() {
		this.effacerEpreuveVide();
		epreuves=epreuveRepository.findAll();
		for (Epreuves epreuveExistante: epreuves)
		{
			//requete pour avoir les groupes d'une epreuve
			//il faut utiliser nom des classes et pas nom des tables(aussi pour les attr)
			TypedQuery<Groupe> requeteGroupe=em.createQuery("select g from Matiere m , Classe c , Groupe g " +
					"WHERE c.nivSpecialite.id=m.nivSpecialite.id AND g.classe.id=c.id AND m.id=?1",Groupe.class);
			requeteGroupe.setParameter(1, epreuveExistante.getMatiere().getId());
			//recuperer les resultats de la requete
			List<Groupe> groupes=requeteGroupe.getResultList();
			//ajouter le 1er groupe trouvé a l'epreuve qui existe deja
			epreuveExistante.setGroupe(groupes.get(0));
			//s'il ya d'autres groupes,on cree d'autre epreuve associé a ces groupes
			if(groupes.size()>1)
			{

				int indice=1;//variable pour fixer position du groupe pour lequel on va creer l'epreuve
				for(int i=0;i<groupes.size()-1;i++)
				{
					//creer epreuve en lui attribuant les memes proprietes de l'epreuve existante mais groupe varie (selon les groupes trouvés par la requete)
					Epreuves epreuveNouvelle=new Epreuves();
					epreuveNouvelle.setNom(epreuveExistante.getNom());
					epreuveNouvelle.setDuree(epreuveExistante.getDuree());
					epreuveNouvelle.setCreneau(epreuveExistante.getCreneau());
					epreuveNouvelle.setMatiere(epreuveExistante.getMatiere());
					epreuveNouvelle.setGroupe(groupes.get(indice));
					indice++;
					em.persist(epreuveNouvelle);
				}
			}
			em.flush();
		}

	}

	@Override
	public void affecterLocaux()
	{
		//requete pour avoir les epreuves(avec groupe: capaciteGroupe+classe) trié par creneau et classe
		//on doit créer un type de donnée qui a les specifications du type retourné par la requete:ResultEpreuveGroupe
		TypedQuery<ResultEpreuveGroupe> requeteEpreuves=em.createQuery("select new com.exam.gestionExams.model.ResultEpreuveGroupe(e,g.capacite,g.classe) from Epreuves e join e.groupe g " +
				"order by e.creneau,g.classe",ResultEpreuveGroupe.class);
		//recuperer les resultats de la requete epreuve
		Collection<ResultEpreuveGroupe> epreuves=requeteEpreuves.getResultList();

		//requete pour avoir les locaux(sans etage0:amphi) avec champ affectation=0
		//on doit créer un type de donnée qui a les specifications du type retourné par la requete:LocalWithAffectation
		TypedQuery<LocalWithAffectation> requeteLocaux=em.createQuery("select new com.exam.gestionExams.model.LocalWithAffectation(l,0) from Local l WHERE l.etage>0",LocalWithAffectation.class);
		//recuperer les resultats de la requete locaux
		Collection<LocalWithAffectation> locaux=requeteLocaux.getResultList();

		for (ResultEpreuveGroupe epreuve: epreuves)
		{
			//filter locaux:trouvez les salles avec cap>capGroupe et ne sont pas affectés au creneau de l'epreuve courante, collect pour convertir streams trouvés dans une list
			List<LocalWithAffectation> salleTrouvées =locaux.stream().filter((local)-> local.getLocal().getCapacite()>= epreuve.getCapacite() && local.getAffectation()!=epreuve.getEpreuve().getCreneau().getId()).collect(Collectors.toList());
			//trié la liste sur etage+cap
			Collections.sort(salleTrouvées,Comparator.comparing((LocalWithAffectation local)-> local.getLocal().getEtage()).thenComparing((LocalWithAffectation local)-> local.getLocal().getCapacite()));
			//si salleTrouvés n'est pas vide,affecter la 1er salle renvoyé a l'epreuve courante puis maj affectation de local
			if(salleTrouvées.size()!=0)
			{
				/*for(int i=0;i<salleTrouvées.size();i++)
				{
					if (salleTrouvées.get(i).getLocal().getCapacite()-epreuve.getCapacite()<10 && salleTrouvées.get(i).getLocal().getCapacite()-epreuve.getCapacite()>=2)
					{
						epreuve.getEpreuve().setLocal(salleTrouvées.get(i).getLocal());
						salleTrouvées.get(i).setAffectation((int)epreuve.getEpreuve().getCreneau().getId());
						break;
					}
					if(epreuve.getEpreuve().getLocal()==null)
					{
						epreuve.getEpreuve().setLocal(salleTrouvées.get(0).getLocal());
						salleTrouvées.get(0).setAffectation((int)epreuve.getEpreuve().getCreneau().getId());
					}
				}*/
				epreuve.getEpreuve().setLocal(salleTrouvées.get(0).getLocal());
				salleTrouvées.get(0).setAffectation((int)epreuve.getEpreuve().getCreneau().getId());
			}
		}
		//ajouter les modifs a la base
		updatingLocalForEpreuve(epreuves);

	}

	public void updatingLocalForEpreuve(Collection<ResultEpreuveGroupe> epreuves) {
		for(ResultEpreuveGroupe epreuve:epreuves) {
			Epreuves e=epreuve.getEpreuve();
			epreuveRepository.saveAndFlush(e);
		}
	}

	@Override
	public void affecterSurv() {

		//requete pour avoir les surveillants permanents
		TypedQuery<SurveillantWithOtherAttribute> requetesurvPermanent=em.createQuery("select new com.exam.gestionExams.model.SurveillantWithOtherAttribute(s,s.nbrHeure) from Surveillant s WHERE s.type='EPI'",SurveillantWithOtherAttribute.class);
		//recuperer les resultats de la requete survPermanent
		Collection<SurveillantWithOtherAttribute> survPermanents=requetesurvPermanent.getResultList();
		//restorer leurs nbr_heure_affected a zero(en cas on exécute l'algo 2fois ou plus)
		for(SurveillantWithOtherAttribute surv:survPermanents) {
			surv.getSurveillant().setNbrHeureAffected(0f);
		}

		//requete pour avoir les surveillants Vacataires
		TypedQuery<SurveillantWithOtherAttribute> requetesurvVacataires=em.createQuery("select new com.exam.gestionExams.model.SurveillantWithOtherAttribute(s,s.nbrHeure) from Surveillant s WHERE s.type!='EPI' OR s.type is null",SurveillantWithOtherAttribute.class);
		//recuperer les resultats de la requete survVacataires
		Collection<SurveillantWithOtherAttribute> survVacataires=requetesurvVacataires.getResultList();
		//restorer leurs nbr_heure_affected a zero(en cas on exécute l'algo 2fois ou plus)
		for(SurveillantWithOtherAttribute surv:survVacataires) {
			surv.getSurveillant().setNbrHeureAffected(0f);
		}

		//trié les creneaux des differents surveillant en ordre croissant
		trierCreneauSurveillant(survPermanents);
		trierCreneauSurveillant(survVacataires);

		//requete pour avoir les epreuves permanents=duree 1.5
		TypedQuery<EpreuvesWithOtherAttribute> requeteEpreuvePermanent=em.createQuery("select new com.exam.gestionExams.model.EpreuvesWithOtherAttribute(e,0) from Epreuves e",EpreuvesWithOtherAttribute.class);
		//recuperer les resultats de la requete EpreuvesPermanent
		Collection<EpreuvesWithOtherAttribute> epreuvesPermanents=requeteEpreuvePermanent.getResultList();

		//recuperer les parametres d'attribution du nbr de surv par epreuve
		TypedQuery<Parameters> requeteParameters=em.createQuery("select p from Parameters p",Parameters.class);
		Parameters parameters=requeteParameters.getSingleResult();

		//affecter nbr de surveillant  de l'epreuve suivant capacité du groupe
		affecterNbrSurveillant(epreuvesPermanents,parameters);

		//repartir les surveillants permanents aux epreuves
		AffectationEpreuvesToSurveillant(survPermanents,true,epreuvesPermanents);

		//calcul heures restant a surveiller
		List<EpreuvesWithOtherAttribute>  epreuvesRestant =epreuvesPermanents.stream().filter((epreuve)-> epreuve.getNbrSurveillant()!=0 ).collect(Collectors.toList());
		float heureRestant=calculHeuresSurveillanceRestant(epreuvesRestant);

		//calcul total des creneau des surv vaca
		int nbrCreneauTotalVaca=calculTotalCrenOfSurvVaca(survVacataires);

		//calcul les heures des vacataires selon heures restant+ses disponibilités
		calculHeureSurveillanceForEachVacataire(survVacataires,heureRestant,nbrCreneauTotalVaca);

		//repartir les surveillants vacataire aux epreuves
		AffectationEpreuvesToSurveillant(survVacataires,false,epreuvesPermanents);

		//enregistrer les données dans bd
		updatingSurveillantsForEpreuve(epreuvesPermanents);
	}

	private int calculTotalCrenOfSurvVaca(Collection<SurveillantWithOtherAttribute> survVacataires) {
		int nbrCreneauTotalVaca=0;
		for (SurveillantWithOtherAttribute surv: survVacataires)
		{
			nbrCreneauTotalVaca+=surv.getSurveillant().getCreneaux().size();
		}
		return nbrCreneauTotalVaca;
	}

	public void updatingSurveillantsForEpreuve(Collection<EpreuvesWithOtherAttribute> epreuves) {
		for(EpreuvesWithOtherAttribute epreuve:epreuves) {
			Epreuves e=epreuve.getEpreuve();
			epreuveRepository.saveAndFlush(e);
		}
	}
	private void AffectationEpreuvesToSurveillant(Collection<SurveillantWithOtherAttribute> surveillants, boolean b,
												  Collection<EpreuvesWithOtherAttribute> epreuvesPermanents) {
		List<EpreuvesWithOtherAttribute>  epreuvesTrouvées;
		for (SurveillantWithOtherAttribute surveillant: surveillants)
		{
			for (Creneau cren: surveillant.getSurveillant().getCreneaux())
			{
				//filter epreuves: on prend les epreuves durant le creneau en cours qui ont nbr de surveillant not null et de durée 1.5
				//(en cas true:affectation surv permanent) , en cas false : affectation surv vacataire
				if(b)
				{
					epreuvesTrouvées =epreuvesPermanents.stream().filter((epreuve)-> epreuve.getEpreuve().getCreneau().getId()== cren.getId() && epreuve.getNbrSurveillant()!=0 && epreuve.getEpreuve().getDuree()==1.5).collect(Collectors.toList());
				}
				else
				{
					epreuvesTrouvées =epreuvesPermanents.stream().filter((epreuve)-> epreuve.getEpreuve().getCreneau().getId()== cren.getId() && epreuve.getNbrSurveillant()!=0).collect(Collectors.toList());

				}
				if(surveillant.getHoursLeft()<=0)
				{
					break;
				}
				if(epreuvesTrouvées.size()!=0)
				{
					//actualiser les infos du surv
					surveillant.setHoursLeft(surveillant.getHoursLeft()-epreuvesTrouvées.get(0).getEpreuve().getDuree());
					surveillant.getSurveillant().setNbrHeureAffected(surveillant.getSurveillant().getNbrHeureAffected()+epreuvesTrouvées.get(0).getEpreuve().getDuree());
					//créer le surveillant qu'on est entrain de le parcourir
					Surveillant s=surveillant.getSurveillant();
					ajoutSurveillantToEpreuve(s,epreuvesTrouvées.get(0).getEpreuve());
					epreuvesTrouvées.get(0).setNbrSurveillant(epreuvesTrouvées.get(0).getNbrSurveillant()-1);
					//actualiser la collection epreuves permenant
					for (EpreuvesWithOtherAttribute epreuvePermanent: epreuvesPermanents)
					{
						if(epreuvePermanent.getEpreuve().getId()== epreuvesTrouvées.get(0).getEpreuve().getId())
						{
							epreuvePermanent=epreuvesTrouvées.get(0);
							break;
						}
					}

				}
			}
		}
	}
	private void ajoutSurveillantToEpreuve(Surveillant s,Epreuves e)
	{
		e.getSurveillants().add(s);
	}

	private void affecterNbrSurveillant(Collection<EpreuvesWithOtherAttribute> epreuvesPermanents,
										Parameters parameters) {

		for (EpreuvesWithOtherAttribute epreuvePermanent: epreuvesPermanents)
		{
			if(epreuvePermanent.getEpreuve().getGroupe().getCapacite()<= parameters.getMaxForOneSurv())
			{
				epreuvePermanent.setNbrSurveillant(1);
			}
			else if(epreuvePermanent.getEpreuve().getGroupe().getCapacite()<= parameters.getMaxForTwoSurv())
			{
				epreuvePermanent.setNbrSurveillant(2);
			}
			else
				epreuvePermanent.setNbrSurveillant(3);

		}

	}

	private void trierCreneauSurveillant(Collection<SurveillantWithOtherAttribute> listeSurveillants) {
		for (SurveillantWithOtherAttribute surveillant: listeSurveillants)
		{
			surveillant.getSurveillant().getCreneaux().sort(Comparator.comparing(Creneau::getId));
		}

	}

	private void calculHeureSurveillanceForEachVacataire(Collection<SurveillantWithOtherAttribute> survVacataires,float nbrHeureSurvRestants,int totalCrenVaca) {
		int nbrCreneauxDisponible=0;
		float pourcentageCreneauParVacataire=0;
		float nbrHeuresParVacataire=0;
		for (SurveillantWithOtherAttribute surv: survVacataires)
		{
			nbrCreneauxDisponible=surv.getSurveillant().getCreneaux().size();
			System.out.println("nbrCreneauxDisponible =" +nbrCreneauxDisponible);
			pourcentageCreneauParVacataire=(float)nbrCreneauxDisponible/totalCrenVaca;
			System.out.println("pourcentageCreneauParVacataire =" +pourcentageCreneauParVacataire);
			nbrHeuresParVacataire=Math.round(nbrHeureSurvRestants*pourcentageCreneauParVacataire);
			surv.setHoursLeft(nbrHeuresParVacataire);//surv.getSurveillant().setNbrHeureAffected(nbrHeuresParVacataire);
			surv.getSurveillant().setNbrHeure(nbrHeuresParVacataire);
			System.out.println("nbrHeuresParVacataire =" +nbrHeuresParVacataire);
		}
	}

	public float calculHeuresSurveillanceRestant(Collection<EpreuvesWithOtherAttribute> epreuvesRestant)
	{
		float nbr=0;
		for (EpreuvesWithOtherAttribute epreuve: epreuvesRestant)
		{
			nbr+=epreuve.getEpreuve().getDuree()*epreuve.getNbrSurveillant();
		}
		return nbr;
	}
}

