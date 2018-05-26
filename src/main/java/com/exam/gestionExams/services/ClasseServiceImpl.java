package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Classe;
import com.exam.gestionExams.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseServiceImpl implements ClasseService {

    @Autowired
    ClasseRepository classeRepository;
    @Override
    public List<Classe> getAll() {
        return classeRepository.findAll();
    }

    @Override
    public Classe saveClasse(Classe classe) {
        return classeRepository.saveAndFlush(classe);
    }

    @Override
    public Classe updateClasse(Classe classe) {
        return classeRepository.saveAndFlush(classe);
    }

    @Override
    public void deleteClasse(Long id) {
        classeRepository.delete(id);
    }
}
