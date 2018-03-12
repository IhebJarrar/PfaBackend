package com.exam.gestionExams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.gestionExams.model.Surveillant;

public interface SurveillantRepository  extends JpaRepository<Surveillant, Long> {

}
