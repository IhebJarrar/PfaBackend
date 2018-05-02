package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantSchedule;
import com.exam.gestionExams.repository.SurveillantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveillantServiceImpl implements SurveillantService {

    @Autowired
    SurveillantRepository surveillantRepository;
    @Override
    public List<Surveillant> getAllTreatedSurveillants() {
        return surveillantRepository.getAllTreatedSurveillants();
    }

    @Override
    public List<Surveillant> getAllNoTreatedSurveillants() {
        return surveillantRepository.getAllNoTreatedSurveillants();
    }

    @Override
    public List<Surveillant> getAllPermannets() {
        return surveillantRepository.getAllPermanents();
    }

    @Override
    public List<Surveillant> getAllVacataires() {
        return surveillantRepository.getAllVacatiare();
    }

    @Override
    public List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve) {

        return surveillantRepository.getSurveillantAvailableInThisCren(epreuve);
    }

	@Override
	public List<SurveillantSchedule> getSurveillantSchedule() {
		// TODO Auto-generated method stub
		return surveillantRepository.getSurveillantSchedule();
	}
}
