package com.exam.gestionExams.model;

public class ResultEpreuveGroupe {

	private Epreuves epreuve;
	private int capacite;
	private Classe classe;
	
	public Epreuves getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuves epreuve) {
		this.epreuve = epreuve;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	public ResultEpreuveGroupe(Epreuves e,int cap,Classe c)
	{
		this.epreuve=e;
		this.capacite=cap;
		this.classe=c;
	}
}
