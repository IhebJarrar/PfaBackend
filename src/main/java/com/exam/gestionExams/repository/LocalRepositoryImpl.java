package com.exam.gestionExams.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Local;

public class LocalRepositoryImpl implements LocalRepositoryCustom{

	@PersistenceContext
    EntityManager em;
	
	@Override
	public List<Local> findLocalesAvailable(Epreuves e) {
		
		TypedQuery<Local> requeteSallesDispo=em.createQuery("select l from Local l where l.id not in (select e.local from Epreuves e where e.local is not null and e.creneau=" +
				e.getCreneau().getId()+")",Local.class);
		List<Local> sallesTrouvées=requeteSallesDispo.getResultList();
		return sallesTrouvées;
	}

}
