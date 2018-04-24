package com.exam.gestionExams.repository;

import java.util.List;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;

public interface SurveillantRepositoryCustom {
	List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve);
}
