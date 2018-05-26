package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRespository extends JpaRepository<Specialite, Long> {
}
