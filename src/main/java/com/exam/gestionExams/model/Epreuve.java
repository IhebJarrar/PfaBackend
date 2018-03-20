package com.exam.gestionExams.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

	@Entity
	public class Epreuve {

		@Id
		@GeneratedValue
		private Long id;
		private String nom;
		private Float duree;
		@ManyToOne
		@JoinColumn(name="local")
		private Local local;
		@ManyToOne
		@JoinColumn(name="creneau")
		private Creneau creneau;
		@ManyToOne
		@JoinColumn(name="groupe")
		private Groupe groupe;
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="matiere")
		private Matiere matiere;
		
		@ManyToMany(mappedBy = "epreuves")
		private List<Surveillant> surveillants=new ArrayList<Surveillant>();
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getNom() {
			return nom;
		}
		public Float getDuree() {
			return duree;
		}
		public void setDuree(Float duree) {
			this.duree = duree;
		}
		public Local getLocal() {
			return local;
		}
		public void setLocal(Local local) {
			this.local = local;
		}
		public Creneau getCreneau() {
			return creneau;
		}
		public void setCreneau(Creneau creneau) {
			this.creneau = creneau;
		}
		public Groupe getGroupe() {
			return groupe;
		}
		public void setGroupe(Groupe groupe) {
			this.groupe = groupe;
		}
		public Matiere getMatiere() {
			return matiere;
		}
		public void setMatiere(Matiere matiere) {
			this.matiere = matiere;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
}
