package com.healthcare_server.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class patientFeatures {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;


private String title;

private String desc;
private String adress;
private String button_name;
private boolean header;
private boolean service;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getButton_name() {
	return button_name;
}
public void setButton_name(String button_name) {
	this.button_name = button_name;
}
public boolean isHeader() {
	return header;
}
public void setHeader(boolean header) {
	this.header = header;
}
public boolean isService() {
	return service;
}
public void setService(boolean service) {
	this.service = service;
}


}
