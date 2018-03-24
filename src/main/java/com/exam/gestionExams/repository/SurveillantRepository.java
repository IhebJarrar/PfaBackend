package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Surveillant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveillantRepository  extends JpaRepository<Surveillant, Long> {

    @Query("select s from Surveillant s where s.creneaux.size > 0")
    public List<Surveillant> getAllTreatedSurveillants();

    @Query("select s from Surveillant s where s.creneaux.size = 0")
    public List<Surveillant> getAllNoTreatedSurveillants();
}
