package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuve;
import com.exam.gestionExams.model.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Transactional
public class EpreuveRepositoryImpl implements EpreuveRepositoryCustom {

	@Autowired
    EpreuveRepository epreuveRepository;
	@PersistenceContext
    EntityManager em;
	
	List<Epreuve> epreuves;

    @Override
	public void effacerEpreuveVide() {
		epreuves=epreuveRepository.findAll();
		for (Epreuve epreuve: epreuves)
		{
			if(epreuve.getNom().equals(""))
				epreuveRepository.delete(epreuve.getId());
		}
	}

    @Override
	public void createEpreuvesForAllGroupes() {
		this.effacerEpreuveVide();
		epreuves=epreuveRepository.findAll();
		for (Epreuve epreuveExistante: epreuves)
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
					Epreuve epreuveNouvelle=new Epreuve();
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

}

