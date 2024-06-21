package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class state {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;


	private String state;
	private int countryId;


	public int getId() {
		return id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getCountryId() {
		return countryId;
	}


	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}


	public void setId(int id) {
		this.id = id;
	}


	

}
