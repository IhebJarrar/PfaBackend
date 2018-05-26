package com.exam.gestionExams.services;

import com.exam.gestionExams.model.Classe;

import java.util.List;

public interface ClasseService {
    List<Classe> getAll();
    Classe saveClasse(Classe classe);
    Classe updateClasse(Classe classe);
    void deleteClasse(Long id);
}
