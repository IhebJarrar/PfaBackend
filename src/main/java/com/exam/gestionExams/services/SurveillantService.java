package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Surveillant;

import java.util.List;

public interface SurveillantService {
    public List<Surveillant> getAllTreatedSurveillants();
    public List<Surveillant> getAllNoTreatedSurveillants();
}
