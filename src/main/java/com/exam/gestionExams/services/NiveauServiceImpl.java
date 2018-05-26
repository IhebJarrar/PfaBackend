package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Niveau;
import com.exam.gestionExams.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauServiceImpl implements NiveauService {

    @Autowired
    NiveauRepository niveauRepository;

    public List<Niveau> getAllLevel() {
        return niveauRepository.findAll();
    }

}
