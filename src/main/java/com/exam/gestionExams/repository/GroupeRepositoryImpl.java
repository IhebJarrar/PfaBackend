package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Classe;
import com.exam.gestionExams.model.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class GroupeRepositoryImpl implements GroupeRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ClasseRepository classeRepository;

    @Override
    public void insertApropriateGroup() {
        List<Classe> classes = classeRepository.findAll();
        for (Classe classe: classes) {
            Groupe[] groupes = new Groupe[classe.getNb_groupe()];
            int indice = 0;
            for(int i = 0; i < groupes.length; i++) {
                groupes[i] = new Groupe();
                indice++;
                groupes[i].setNom(classe.getNom() + "G" + indice);
                groupes[i].setClasse(classe);
                em.persist(groupes[i]);
            }
            em.flush();
        }
    }
}
