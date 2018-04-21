package com.exam.gestionExams.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.gestionExams.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long>,LocalRepositoryCustom {
	
}
