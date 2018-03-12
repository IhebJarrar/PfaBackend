package com.exam.gestionExams.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

	@Entity
	public class Creneau {

		@Id
		@GeneratedValue
		private Long id;
		@Temporal(TemporalType.DATE)
		private Date date;
		@ManyToOne
		@JoinColumn(name="seance")
		private Seance seance;
		
		@ManyToMany
		private List<Surveillant> surveillants=new ArrayList<Surveillant>();

		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		public Seance getSeance() {
			return seance;
		}
		public void setSeance(Seance seance) {
			this.seance = seance;
		}
	}

