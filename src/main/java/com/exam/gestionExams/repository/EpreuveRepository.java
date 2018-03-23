package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuves;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpreuveRepository extends JpaRepository<Epreuves, Long>, EpreuveRepositoryCustom {

}
