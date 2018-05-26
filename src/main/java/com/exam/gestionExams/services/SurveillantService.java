package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Surveillant;
import com.exam.gestionExams.model.SurveillantSchedule;

import java.util.List;

public interface SurveillantService {
    public List<Surveillant> getAllTreatedSurveillants();
    public List<Surveillant> getAllNoTreatedSurveillants();
    public List<Surveillant> getAllPermannets();
    public List<Surveillant> getAllVacataires();
    List<Surveillant> getSurveillantAvailableInThisCren(Epreuves epreuve);
    List<SurveillantSchedule> getSurveillantSchedule();
}
