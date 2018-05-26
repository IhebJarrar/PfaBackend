package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Matiere;
import com.exam.gestionExams.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereServiceImpl implements MatiereService {

    @Autowired
    MatiereRepository matiereRepository;

    @Override
    public List<Matiere> getAll() {
        return matiereRepository.findAll();
    }
}
