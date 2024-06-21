package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class insurancePlan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int coverageLimit;
	private int premium;
	private String frequency;
	private String planName;
	private int insuranceProvider;
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getCoverageLimit() {
		return coverageLimit;
	}
	public void setCoverageLimit(int coverageLimit) {
		this.coverageLimit = coverageLimit;
	}
	public int getPremium() {
		return premium;
	}
	public void setPremium(int premium) {
		this.premium = premium;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public int getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(int insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
