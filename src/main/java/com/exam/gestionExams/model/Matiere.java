package com.exam.gestionExams.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Matiere {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	@ManyToOne
    @JoinColumn(name = "nivSpecialite")
    private NivSpecialite nivSpecialite;
	
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
	public NivSpecialite getNivSpecialite() {
		return nivSpecialite;
	}
	public void setNivSpecialite(NivSpecialite nivSpecialite) {
		this.nivSpecialite = nivSpecialite;
	}
	
	
}
