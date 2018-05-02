package com.exam.gestionExams.model;

public class SurveillantWithOtherAttribute {

	private Surveillant surveillant;
	private float hoursLeft;
	public SurveillantWithOtherAttribute(Surveillant surveillant, float hoursLeft) {
		super();
		this.surveillant = surveillant;
		this.hoursLeft = hoursLeft;
	}
	public Surveillant getSurveillant() {
		return surveillant;
	}
	public void setSurveillant(Surveillant surveillant) {
		this.surveillant = surveillant;
	}

	public float getHoursLeft() {
		return hoursLeft;
	}
	public void setHoursLeft(float hoursLeft) {
		this.hoursLeft = hoursLeft;
	}
	
}
