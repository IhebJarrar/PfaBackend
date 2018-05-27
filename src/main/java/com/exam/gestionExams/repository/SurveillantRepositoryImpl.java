package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantSchedule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class SurveillantRepositoryImpl implements SurveillantRepositoryCustom {

	@PersistenceContext
    EntityManager em;

	@Override
	public List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve) {

		TypedQuery<Surveillant> requeteSurveillantDispo=em.createQuery("SELECT s from Surveillant s JOIN s.creneaux d WHERE d="+epreuve.getCreneau().getId()
						+ "and s.id not in (SELECT s1.id FROM Surveillant s1 JOIN s1.epreuves su  WHERE su.creneau="+epreuve.getCreneau().getId()
						+")"//+ ") and s.nbrHeure*60> (SELECT SUM(su.duree*60) from Surveillant s2 JOIN s2.epreuves su WHERE s2.id=s.id)"
				,Surveillant.class);
		List<Surveillant> surveillantTrouvés=requeteSurveillantDispo.getResultList();
		return surveillantTrouvés;
	}

	@Override
	public List<SurveillantSchedule> getSurveillantSchedule() {
		TypedQuery<SurveillantSchedule> requeteSurveillantSchedule=em.createQuery("select new com.exam.gestionExams.model.SurveillantSchedule(s.nom,s.type,s.nbrHeure,s.nbrHeureAffected,su.nom,su.duree,l.nom,l.etage,c.date,se.heureDebut)"
				+ "from Surveillant s join s.epreuves su join su.creneau c join c.seance se join su.local l order by s.nom",SurveillantSchedule.class);
		List<SurveillantSchedule> surveillantSchedule=requeteSurveillantSchedule.getResultList();
		return surveillantSchedule;
	}

}
 

 
