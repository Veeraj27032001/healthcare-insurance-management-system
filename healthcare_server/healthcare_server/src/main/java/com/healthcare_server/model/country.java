package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class country {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;


	private String country;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	

}
