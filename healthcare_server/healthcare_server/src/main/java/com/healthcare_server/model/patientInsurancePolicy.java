package com.healthcare_server.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class patientInsurancePolicy {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String policyHolderName;
@Temporal(TemporalType.DATE)
private Date broughtDate;
private int planId;
private int paidAmt;
private String policyNo;
private int patientId;
private String identificationNo;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getBroughtDate() {
	return broughtDate;
}
public void setBroughtDate(Date broughtDate) {
	this.broughtDate = broughtDate;
}

public int getPlanId() {
	return planId;
}
public void setPlanId(int planId) {
	this.planId = planId;
}
public int getPaidAmt() {
	return paidAmt;
}
public void setPaidAmt(int paidAmt) {
	this.paidAmt = paidAmt;
}
public String getPolicyNo() {
	return policyNo;
}
public void setPolicyNo(String policyNo) {
	this.policyNo = policyNo;
}
public int getPatientId() {
	return patientId;
}
public void setPatientId(int patientId) {
	this.patientId = patientId;
}
public String getIdentificationNo() {
	return identificationNo;
}
public void setIdentificationNo(String identificationNo) {
	this.identificationNo = identificationNo;
}
public String getPolicyHolderName() {
	return policyHolderName;
}
public void setPolicyHolderName(String policyHolderName) {
	this.policyHolderName = policyHolderName;
}


}
