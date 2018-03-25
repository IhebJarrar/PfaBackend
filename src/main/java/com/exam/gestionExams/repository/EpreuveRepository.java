package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuves;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpreuveRepository extends JpaRepository<Epreuves, Long>, EpreuveRepositoryCustom {

    @Query("select e from Epreuves s ")
    public List<Epreuves> getAllEpreuves();

}
