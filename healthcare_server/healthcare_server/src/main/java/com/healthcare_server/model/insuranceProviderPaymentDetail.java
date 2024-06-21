package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class insuranceProviderPaymentDetail {
	@Id
	private int id;

 private String secretKey;
 
 private String keyId;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getSecretKey() {
	return secretKey;
}

public void setSecretKey(String secretKey) {
	this.secretKey = secretKey;
}

public String getKeyId() {
	return keyId;
}

public void setKeyId(String keyId) {
	this.keyId = keyId;
}


}