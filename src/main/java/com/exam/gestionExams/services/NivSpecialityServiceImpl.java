package com.exam.gestionExams.services;

import com.exam.gestionExams.model.NivSpecialite;
import com.exam.gestionExams.repository.NivSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivSpecialityServiceImpl implements NivSpecialityService {

    @Autowired
    NivSpecialityRepository nivSpecialityRepository;

    @Override
    public NivSpecialite getNivSpecialityByLevelAndSpec(Integer level, Long speciality) {
        return nivSpecialityRepository.getSpecialityLevelId(level, speciality);
    }
}
