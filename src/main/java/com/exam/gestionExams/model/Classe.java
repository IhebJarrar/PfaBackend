package com.exam.gestionExams.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Classe {

		@Id
		@GeneratedValue
		private Long id;
		private String nom;
		private int capacite;
		private int nb_groupe;
		@ManyToOne
		@JoinColumn(name="specialite")
		private Specialite specialite;
		
		
		public Specialite getSpecialite() {
			return specialite;
		}
		public void setSpecialite(Specialite specialite) {
			this.specialite = specialite;
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

	public void setId(Long id) {
		this.id = id;
	}

	public int getNb_groupe() {
		return nb_groupe;
	}

	public void setNb_groupe(int nb_groupe) {
		this.nb_groupe = nb_groupe;
	}
}
