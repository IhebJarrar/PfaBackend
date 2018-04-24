package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;

import java.util.List;

public interface SurveillantService {
    public List<Surveillant> getAllTreatedSurveillants();
    public List<Surveillant> getAllNoTreatedSurveillants();
    public List<Surveillant> getAllTreatedPermanents();
    public List<Surveillant> getAllNoTreatedVacataires();
    public List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve) ;
}
