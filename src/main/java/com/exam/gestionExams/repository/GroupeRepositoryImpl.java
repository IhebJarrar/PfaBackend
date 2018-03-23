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
            Groupe[] groupes = new Groupe[classe.getNbrGroupe()];
            int[] capacite=new int[4];//capacite groupe est un tab de 4 case
            capacite[3]=classe.getCapacite();//dans la derniere case,on stoque capacite classe et les 3 premiers on stoque les cap des groupes
            if(groupes.length==2)//si classe a 2 groupe
            {
            		capacite[0]=(int) Math.ceil(capacite[3]/2.0);//groupe 1 aura un demi;ceil fct qui prend valeur sup
            		capacite[1]=capacite[3]-capacite[0];//groupe 2 aura le reste
            	
            }
            else if(groupes.length==3)//si classe a 3 groupe
            {
            	    capacite[0]=(int) Math.ceil(capacite[3]/3.0);//groupe 1 aura un tier;ceil fct qui prend valeur sup
            	    capacite[1]=(int) Math.ceil(capacite[3]/3.0);//groupe 2 aura un tier
            	    capacite[2]=capacite[3]-(capacite[0]+capacite[1]);//groupe 3 aura le reste
            	
            }
            else
            	capacite[0]=capacite[3];//classe a 1 groupe donc il prend la cap de la classe
            int indice = 0;
            for(int i = 0; i < groupes.length; i++) {
                groupes[i] = new Groupe();
                indice++;
                groupes[i].setNom(classe.getNom() + "G" + indice);
                groupes[i].setClasse(classe);
                groupes[i].setCapacite(capacite[i]);
                em.persist(groupes[i]);
            }
            em.flush();
        }
    }
}
