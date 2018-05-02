package com.exam.gestionExams.services;

import java.util.List;

import com.exam.gestionExams.model.Epreuves;

public interface EpreuveService {
	
    List<Epreuves> getAllEpreuves();
    Epreuves getEpreuve(Long id);
}
