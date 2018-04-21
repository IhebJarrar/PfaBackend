package com.exam.gestionExams.repository;

import java.util.List;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Local;

public interface LocalRepositoryCustom {
	List<Local> findLocalesAvailable(Epreuves e);
}
