package com.healthcare_server.component;

import org.springframework.stereotype.Component;

@Component
public class renewRequest {
private int renewId;
private long amt;
private String policyNo;
private RazorpayResponse  response;
public int getRenewId() {
	return renewId;
}
public void setRenewId(int renewId) {
	this.renewId = renewId;
}
public long getAmt() {
	return amt;
}
public void setAmt(long amt) {
	this.amt = amt;
}
public String getPolicyNo() {
	return policyNo;
}
public void setPolicyNo(String policyNo) {
	this.policyNo = policyNo;
}
public RazorpayResponse getResponse() {
	return response;
}
public void setResponse(RazorpayResponse response) {
	this.response = response;
}







}
