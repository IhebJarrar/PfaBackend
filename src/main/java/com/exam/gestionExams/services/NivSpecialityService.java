package com.exam.gestionExams.services;

import com.exam.gestionExams.model.NivSpecialite;
import org.springframework.stereotype.Service;

@Service
public interface NivSpecialityService {
    NivSpecialite getNivSpecialityByLevelAndSpec(Integer level, Long speciality);
}
