package com.exam.gestionExams.model;

import java.util.Date;

public class SurveillantSchedule {
private String nomSurv;
private String type;
private Float nbrHeure;
private Float nbrHeureAffected;
private String nomEpreuve;
private Float durée;
private String nomSalle;
private int etage;
private Date date;
private String heureDebut;

public SurveillantSchedule(String nomSurv, String type, Float nbrHeure, Float nbrHeureAffected, String nomEpreuve,
                           Float durée, String nomSalle, int etage, Date date, String heureDebut) {
	super();
	this.nomSurv = nomSurv;
	this.type = type;
	this.nbrHeure = nbrHeure;
	this.nbrHeureAffected = nbrHeureAffected;
	this.nomEpreuve = nomEpreuve;
	this.durée = durée;
	this.nomSalle = nomSalle;
	this.etage = etage;
	this.date = date;
	this.heureDebut = heureDebut;
}
public String getNomSurv() {
	return nomSurv;
}
public void setNomSurv(String nomSurv) {
	this.nomSurv = nomSurv;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Float getNbrHeure() {
	return nbrHeure;
}
public void setNbrHeure(Float nbrHeure) {
	this.nbrHeure = nbrHeure;
}
public Float getNbrHeureAffected() {
	return nbrHeureAffected;
}
public void setNbrHeureAffected(Float nbrHeureAffected) {
	this.nbrHeureAffected = nbrHeureAffected;
}
public String getNomEpreuve() {
	return nomEpreuve;
}
public void setNomEpreuve(String nomEpreuve) {
	this.nomEpreuve = nomEpreuve;
}
public Float getDurée() {
	return durée;
}
public void setDurée(Float durée) {
	this.durée = durée;
}
public String getNomSalle() {
	return nomSalle;
}
public void setNomSalle(String nomSalle) {
	this.nomSalle = nomSalle;
}
public int getEtage() {
	return etage;
}
public void setEtage(int etage) {
	this.etage = etage;
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

}
