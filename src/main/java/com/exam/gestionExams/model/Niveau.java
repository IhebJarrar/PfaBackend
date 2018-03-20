package com.exam.gestionExams.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Niveau {
	@Id
	@GeneratedValue
	private int niveau;
	@OneToMany(mappedBy = "specialite")
	private List<NivSpecialite> nivSpecialites=new ArrayList<NivSpecialite>();
	
	
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	
}
