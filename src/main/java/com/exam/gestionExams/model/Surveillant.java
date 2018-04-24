package com.exam.gestionExams.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Surveillant {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String type;
	private Float nbrHeure;
	@ManyToMany(mappedBy = "surveillants")
	@JsonIgnore
	private List<Epreuves> epreuves=new ArrayList<Epreuves>();
	@ManyToMany
	@JoinTable(name = "disponibilite", joinColumns = @JoinColumn(name = "surv_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "creneau_id", referencedColumnName = "id"))
	private List<Creneau> creneaux=new ArrayList<Creneau>();
	
	
	public List<Epreuves> getEpreuves() {
		return epreuves;
	}
	public void setEpreuves(List<Epreuves> epreuves) {
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
	public Float getNbrHeure() {
		return nbrHeure;
	}
	public void setNbrHeure(Float nbrHeure) {
		this.nbrHeure = nbrHeure;
	}
	
	
}
