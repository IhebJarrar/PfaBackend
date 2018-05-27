package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.NivSpecialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivSpecialityRepository {
    NivSpecialite getSpecialityLevelId(Integer niveau, String specialiteName);
}
