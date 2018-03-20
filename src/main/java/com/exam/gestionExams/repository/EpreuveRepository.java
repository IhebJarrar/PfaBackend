package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long>, EpreuveRepositoryCustom {

}
