package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departement, Long> {
}
