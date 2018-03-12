package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends JpaRepository<Groupe, Long>, GroupeRepositoryCustom {
}
