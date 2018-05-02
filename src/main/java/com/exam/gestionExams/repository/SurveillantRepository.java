package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Surveillant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveillantRepository  extends JpaRepository<Surveillant, Long>, SurveillantRepositoryCustom {

    @Query("select s from Surveillant s where s.creneaux.size > 0")
    List<Surveillant> getAllTreatedSurveillants();

    @Query("select s from Surveillant s where s.creneaux.size = 0")
    List<Surveillant> getAllNoTreatedSurveillants();

    @Query("select s from Surveillant s where s.type = 'EPI'")
    List<Surveillant> getAllPermanents();

    @Query("select s from Surveillant s where s.type != 'EPI' or s.type is null")
    List<Surveillant> getAllVacatiare();
}
