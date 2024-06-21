package com.healthcare_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class fees {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;


	private int counsultanceFees;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCounsultanceFees() {
		return counsultanceFees;
	}
	public void setCounsultanceFees(int counsultanceFees) {
		this.counsultanceFees = counsultanceFees;
	}
	


}