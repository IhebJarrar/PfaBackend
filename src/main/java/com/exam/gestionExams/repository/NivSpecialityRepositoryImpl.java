package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.NivSpecialite;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class NivSpecialityRepositoryImpl implements NivSpecialityRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public NivSpecialite getSpecialityLevelId(Integer niveau, Long specialiteId) {
        System.out.println("niveau:" + niveau + "spec: " + specialiteId);
        System.out.println("getNivSpecId");
        TypedQuery<NivSpecialite> query = em.createQuery("select nv from NivSpecialite nv " +
                "where nv.niveau.niveau = :niveau and nv.specialite.id = :specId", NivSpecialite.class);
        query.setParameter("niveau", niveau);
        query.setParameter("specId", specialiteId);
        List<NivSpecialite> nivSpecialites = query.getResultList();
        if(nivSpecialites.isEmpty()) {
            NivSpecialite nivSpecialite = new NivSpecialite();
            System.out.println("niv spec id" + nivSpecialite.getId());
            return nivSpecialite;
        }
        return nivSpecialites.get(0);
    }
}
