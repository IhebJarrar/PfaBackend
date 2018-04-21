package com.exam.gestionExams.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parameters {
@Id
@GeneratedValue
private Long id;
private int maxForOneSurv;
private int maxForTwoSurv;
public int getMaxForOneSurv() {
	return maxForOneSurv;
}
public void setMaxForOneSurv(int maxForOneSurv) {
	this.maxForOneSurv = maxForOneSurv;
}
public int getMaxForTwoSurv() {
	return maxForTwoSurv;
}
public void setMaxForTwoSurv(int maxForTwoSurv) {
	this.maxForTwoSurv = maxForTwoSurv;
}

}
