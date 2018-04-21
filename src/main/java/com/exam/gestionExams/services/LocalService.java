package com.exam.gestionExams.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exam.gestionExams.model.Epreuves;
import com.exam.gestionExams.model.Local;

@Service
public interface LocalService {
    List<Local> getAllLocal();
    List<Local> getLocauxAvailable(Epreuves e);
}
