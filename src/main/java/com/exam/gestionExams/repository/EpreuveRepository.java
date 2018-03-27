package com.exam.gestionExams.repository;

import com.exam.gestionExams.model.Epreuves;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpreuveRepository extends JpaRepository<Epreuves, Long>, EpreuveRepositoryCustom {

	@Query("select new com.exam.gestionExams.model.AfficherEpreuves(e.id,cr.date,s.heureDebut,e.nom,e.duree,c.nom,g.nom,g.capacite,l) "
			+ "from Epreuves e left outer join e.local l join e.groupe g join g.classe c join e.creneau cr join cr.seance s order by cr.date,s.heureDebut")
    public List<Epreuves> getAllEpreuves();

}
