package com.exam.gestionExams.model;

public class SurveillantWithOtherAttribute {

	private Surveillant surveillant;
	private float hoursAffected;
	private float hoursLeft;
	public SurveillantWithOtherAttribute(Surveillant surveillant, float hoursAffected, float hoursLeft) {
		super();
		this.surveillant = surveillant;
		this.hoursAffected = hoursAffected;
		this.hoursLeft = hoursLeft;
	}
	public Surveillant getSurveillant() {
		return surveillant;
	}
	public void setSurveillant(Surveillant surveillant) {
		this.surveillant = surveillant;
	}
	public float getHoursAffected() {
		return hoursAffected;
	}
	public void setHoursAffected(float hoursAffected) {
		this.hoursAffected = hoursAffected;
	}
	public float getHoursLeft() {
		return hoursLeft;
	}
	public void setHoursLeft(float hoursLeft) {
		this.hoursLeft = hoursLeft;
	}
	
}
