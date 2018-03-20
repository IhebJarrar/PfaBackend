package com.exam.gestionExams.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

	@Entity
	public class Specialite {

		@Id
		@GeneratedValue
		private Long id;
		private String nom;
		@ManyToOne
		@JoinColumn(name="departement")
		private Departement departement;
		@OneToMany(mappedBy = "niveau")
		private List<NivSpecialite> nivSpecialites=new ArrayList<NivSpecialite>();
		
		
		public Departement getDepartement() {
			return departement;
		}
		public void setDepartement(Departement departement) {
			this.departement = departement;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		
}
