package com.exam.gestionExams.model;

import java.util.Date;

public class AfficherEpreuves {
	
private Long idEpreuve;
private Date date;
private String heureDebut;
private String nom;
private Float duree;

private String nomClasse;
private String groupe;
private int capaciteGroupe;
private Local salle;
/*
private Long idSalle;
private String nomSalle;
private int capaciteSalle;
private int etage;*/

public AfficherEpreuves(Long idEpreuve, Date date, String heureDebut, String nom, Float duree, String nomClasse,
		String groupe, int capaciteGroupe, Local salle) {
	this.idEpreuve = idEpreuve;
	this.date = date;
	this.heureDebut = heureDebut;
	this.nom = nom;
	this.duree = duree;
	this.nomClasse = nomClasse;
	this.groupe = groupe;
	this.capaciteGroupe = capaciteGroupe;
	this.salle=salle;
	/*
	this.idSalle = idSalle;
	this.nomSalle = nomSalle;
	this.capaciteSalle = capaciteSalle;
	this.etage = etage;*/
	
	
}
public Long getIdEpreuve() {
	return idEpreuve;
}
public void setIdEpreuve(Long idEpreuve) {
	this.idEpreuve = idEpreuve;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getHeureDebut() {
	return heureDebut;
}
public void setHeureDebut(String heureDebut) {
	this.heureDebut = heureDebut;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public Float getDuree() {
	return duree;
}
public void setDuree(Float duree) {
	this.duree = duree;
}
public String getClasse() {
	return nomClasse;
}
public void setClasse(String classe) {
	this.nomClasse = classe;
}
public String getGroupe() {
	return groupe;
}
public void setGroupe(String groupe) {
	this.groupe = groupe;
}
public int getCapaciteGroupe() {
	return capaciteGroupe;
}
public void setCapaciteGroupe(int capaciteGroupe) {
	this.capaciteGroupe = capaciteGroupe;
}
/*
public Long getIdSalle() {
	return idSalle;
}
public void setIdSalle(Long idSalle) {
	this.idSalle = idSalle;
}
public String getNomSalle() {
	return nomSalle;
}
public void setNomSalle(String nomSalle) {
	this.nomSalle = nomSalle;
}
public int getCapaciteSalle() {
	return capaciteSalle;
}
public void setCapaciteSalle(int capaciteSalle) {
	this.capaciteSalle = capaciteSalle;
}
public int getEtage() {
	return etage;
}
public void setEtage(int etage) {
	this.etage = etage;
}
*/
public Local getSalle() {
	return salle;
}
public void setSalle(Local salle) {
	this.salle = salle;
}


}