package com.exam.gestionExams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Local;
import com.exam.gestionExams.repository.LocalRepository;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    LocalRepository localeRepository;

    @Override
    public List<Local> getAllLocal() {
        return localeRepository.findAll();
    }

	@Override
	public List<Local> getLocauxAvailable(Epreuves e) {
		// TODO Auto-generated method stub
		return localeRepository.findLocalesAvailable(e);
	}
}