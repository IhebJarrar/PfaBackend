package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CreneauRepository extends JpaRepository<Creneau, Long> {

    @Query("select distinct c.date from Creneau c")
    List<Date> findAllDistinct();
}
