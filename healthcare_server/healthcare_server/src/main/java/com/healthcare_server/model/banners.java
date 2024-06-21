package com.healthcare_server.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class banners {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;
   private String paragraph; 
   private String header;
   @Lob
   private byte[] img;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getParagraph() {
	return paragraph;
}
public void setParagraph(String paragraph) {
	this.paragraph = paragraph;
}
public String getHeader() {
	return header;
}
public void setHeader(String header) {
	this.header = header;
}
public byte[] getImg() {
	return img;
}
public void setImg(byte[] img) {
	this.img = img;
}
   
}