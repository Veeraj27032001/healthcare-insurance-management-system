package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class planNdcCoverage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int ndcId;
	 private int planId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNdcId() {
		return ndcId;
	}
	public void setNdcId(int ndcId) {
		this.ndcId = ndcId;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	 
}
