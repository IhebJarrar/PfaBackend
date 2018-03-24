package com.exam.gestionExams.model;

public class LocalWithAffectation {
private Local local;
private int affectation;

public Local getLocal() {
	return local;
}
public void setLocal(Local local) {
	this.local = local;
}
public int getAffectation() {
	return affectation;
}
public void setAffectation(int affectation) {
	this.affectation = affectation;
}
public LocalWithAffectation(Local local, int affectation) {
	this.local = local;
	this.affectation = affectation;
}

}
