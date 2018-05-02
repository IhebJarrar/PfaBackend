package com.exam.gestionExams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.repository.EpreuveRepository;
@Service
public class EpreuveServiceImpl implements EpreuveService {

	@Autowired
    EpreuveRepository epreuveRepository;
	
	public List<Epreuves> getAllEpreuves() {
		return epreuveRepository.findAll();
	}

	@Override
	public Epreuves getEpreuve(Long id) {
		return epreuveRepository.findOne(id);
	}
}
