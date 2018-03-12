package com.exam.gestionExams.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Surveillant {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String type;
	@ManyToMany
	private List<Epreuve> epreuves=new ArrayList<Epreuve>();
	@ManyToMany
	@JoinTable(name="disponibilite")
	private List<Creneau> creneaux=new ArrayList<Creneau>();
	
	public List<Epreuve> getEpreuves() {
		return epreuves;
	}
	public void setEpreuves(List<Epreuve> epreuves) {
		this.epreuves = epreuves;
	}
	public List<Creneau> getCreneaux() {
		return creneaux;
	}
	public void setCreneaux(List<Creneau> creneaux) {
		this.creneaux = creneaux;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
