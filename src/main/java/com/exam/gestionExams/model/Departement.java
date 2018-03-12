package com.exam.gestionExams.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

	@Entity
	public class Departement {

		@Id
		@GeneratedValue
		private Long id;
		private String nom;
		@ManyToOne
		@JoinColumn(name="ecole")
		private Ecole ecole;
		
		public Ecole getEcole() {
			return ecole;
		}
		public void setEcole(Ecole ecole) {
			this.ecole = ecole;
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
