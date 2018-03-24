package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Groupe;
import com.exam.gestionExams.model.LocalWithAffectation;
import com.exam.gestionExams.model.ResultEpreuveGroupe;

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

}

