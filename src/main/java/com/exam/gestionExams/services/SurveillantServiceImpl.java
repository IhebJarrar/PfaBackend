package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Surveillant;
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
}
