package com.exam.gestionExams.model;

public class EpreuvesWithOtherAttribute {
private Epreuves epreuve;
private int nbrSurveillant;
public EpreuvesWithOtherAttribute(Epreuves epreuve, int nbrSurveillant) {
	super();
	this.epreuve = epreuve;
	this.nbrSurveillant = nbrSurveillant;
}
public Epreuves getEpreuve() {
	return epreuve;
}
public void setEpreuve(Epreuves epreuve) {
	this.epreuve = epreuve;
}
public int getNbrSurveillant() {
	return nbrSurveillant;
}
public void setNbrSurveillant(int nbrSurveillant) {
	this.nbrSurveillant = nbrSurveillant;
}

}
