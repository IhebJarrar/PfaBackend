package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Specialite;
import com.exam.gestionExams.repository.SpecialityRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    SpecialityRespository specialityRespository;

    @Override
    public List<Specialite> getAll() {
        return specialityRespository.findAll();
    }
}
