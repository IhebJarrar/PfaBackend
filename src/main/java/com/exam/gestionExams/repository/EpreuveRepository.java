package com.exam.gestionExams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.gestionExams.model.Epreuve;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long>, EpreuveRepositoryCustom{

}
