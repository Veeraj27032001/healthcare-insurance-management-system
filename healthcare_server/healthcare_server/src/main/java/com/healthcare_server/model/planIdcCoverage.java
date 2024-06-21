package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class planIdcCoverage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int idcId;
	 private int planId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcId() {
		return idcId;
	}
	public void setIdcId(int idcId) {
		this.idcId = idcId;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	 
}
