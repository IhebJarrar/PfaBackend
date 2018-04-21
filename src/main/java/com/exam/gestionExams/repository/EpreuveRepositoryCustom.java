
package com.exam.gestionExams.repository;

import java.util.Collection;

import com.exam.gestionExams.model.ResultEpreuveGroupe;

public interface EpreuveRepositoryCustom {
	void effacerEpreuveVide();
	void createEpreuvesForAllGroupes();
	void affecterLocaux();
	void updatingLocalForEpreuve(Collection<ResultEpreuveGroupe> epreuves);
	void affecterSurv();
		
}
