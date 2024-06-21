package com.healthcare_server.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
public class insuranceRenewal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	private boolean paymentStatus;
	private String patientPolicyNo;
	private boolean renewalShow;
	@Column(columnDefinition="JSON")
	private String paymentDetail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPatientPolicyNo() {
		return patientPolicyNo;
	}
	public void setPatientPolicyNo(String patientPolicyNo) {
		this.patientPolicyNo = patientPolicyNo;
	}
	public boolean isRenewalShow() {
		return renewalShow;
	}
	public void setRenewalShow(boolean renewalShow) {
		this.renewalShow = renewalShow;
	}
	public String getPaymentDetail() {
		return paymentDetail;
	}
	public void setPaymentDetail(String paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	
	
	

}
