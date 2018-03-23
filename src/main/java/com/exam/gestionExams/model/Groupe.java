package com.exam.gestionExams.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


	@Entity
	public class Groupe  {

		@Id
		@GeneratedValue
		private Long id;
		private String nom;
		private int capacite;
		@ManyToOne
		@JoinColumn(name="classe")
		private Classe classe;
		
		public Classe getClasse() {
			return classe;
		}
		public void setClasse(Classe classe) {
			this.classe = classe;
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
		public int getCapacite() {
			return capacite;
		}
		public void setCapacite(int capacite) {
			this.capacite = capacite;
		}
}

