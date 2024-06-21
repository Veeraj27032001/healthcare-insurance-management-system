package com.healthcare_server.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class SiteDetails {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;
   private String title; 
   private String desc;
   @Lob
   private byte[] logo;
   
 public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

public byte[] getLogo() {
	return logo;
}
public void setLogo(byte[] logo) {
	this.logo = logo;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}

 
 
}
