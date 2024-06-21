package com.healthcare_server.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare_server.model.adminFeatures;
import com.healthcare_server.model.banners;
import com.healthcare_server.model.insuranceproviderFeatures;
import com.healthcare_server.model.patientFeatures;
import com.healthcare_server.respositary.adminFeaturesRepository;
import com.healthcare_server.respositary.bannersRepository;
import com.healthcare_server.respositary.insuranceProviderFeaturesRepository;
import com.healthcare_server.respositary.patientFeaturesRepository;
import com.healthcare_server.respositary.siteDetailsRepository;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class SiteDetails {
	

  @Autowired
  private siteDetailsRepository siteDetailRepo;
  @Autowired
  private bannersRepository bannerRepo;
  @Autowired
  private patientFeaturesRepository PatientFeaturesRepo;
  
  @Autowired
  private adminFeaturesRepository adminFeaturesRepo;
  @Autowired
  private insuranceProviderFeaturesRepository insuranceProviderFeaturesRepo;
  
    @CrossOrigin("http://localhost:3000")
	@RequestMapping("logo")
	void getLogo(HttpServletResponse response) throws IOException{
	      
	  response.setContentType(MediaType.IMAGE_PNG_VALUE);
	  response.getOutputStream().write(siteDetailRepo.findLogoById(1));
	}
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("name")
	String getName() {
		
	    return siteDetailRepo.findTitleById(1);
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/ping")
	Boolean getping() {
	return true;
	}
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("desc")
	String getDesc() {
		
	    return siteDetailRepo.findDescById(1);
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/banners")
	List<banners> getbanners() {
		
	    return bannerRepo.findAll() ;
	}
	
	
	

	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("patientFeatures")
	List<patientFeatures> getPatientFeatures() {
		
	return PatientFeaturesRepo.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("adminFeatures")
	List<adminFeatures> getadminFeatures() {
		
	return adminFeaturesRepo.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("insuranceProviderFeatures")
	List<insuranceproviderFeatures> getInsuranceProviderFeatures() {
		
	return insuranceProviderFeaturesRepo.findAll();
	}

}
