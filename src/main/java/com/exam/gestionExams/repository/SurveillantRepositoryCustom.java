package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantSchedule;

import java.util.List;

public interface SurveillantRepositoryCustom {
	List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve);
	List<SurveillantSchedule> getSurveillantSchedule() ;
}
