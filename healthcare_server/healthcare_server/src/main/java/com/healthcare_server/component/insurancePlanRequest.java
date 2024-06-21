package com.healthcare_server.component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class insurancePlanRequest {
	
	
	private String planNo;
	private int coverageLimit;
	private int premium;
	private String frequency;
	private String planName;
	private int insuranceProvider;
	private boolean status;
	private int cptId[];
	private int idcId[];
	private int ndcId[];
	
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
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
	public int[] getCptId() {
		return cptId;
	}
	public void setCptId(int[] cptId) {
		this.cptId = cptId;
	}
	public int[] getIdcId() {
		return idcId;
	}
	public void setIdcId(int[] idcId) {
		this.idcId = idcId;
	}
	public int[] getNdcId() {
		return ndcId;
	}
	public void setNdcId(int[] ndcId) {
		this.ndcId = ndcId;
	}
	
	
}
