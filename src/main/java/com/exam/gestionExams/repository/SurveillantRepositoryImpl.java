package com.exam.gestionExams.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;

public class SurveillantRepositoryImpl implements SurveillantRepositoryCustom {

	@PersistenceContext
    EntityManager em;
	
	@Override
	public List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve) {
		
		TypedQuery<Surveillant> requeteSurveillantDispo=em.createQuery("SELECT s from Surveillant s JOIN s.creneaux d WHERE d="+epreuve.getCreneau().getId()
				+ "and s.id not in (SELECT s1.id FROM Surveillant s1 JOIN s1.epreuves su  WHERE su.creneau="+epreuve.getCreneau().getId()
				+ ") and s.nbrHeure*60> (SELECT SUM(su.duree*60) from Surveillant s2 JOIN s2.epreuves su WHERE s2.id=s.id)"
				,Surveillant.class);
		List<Surveillant> surveillantTrouvés=requeteSurveillantDispo.getResultList();
		return surveillantTrouvés;
	}

}
 

 
