
package com.healthcare_server.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.healthcare_server.component.loginRequest;
import com.healthcare_server.model.city;
import com.healthcare_server.model.country;
import com.healthcare_server.model.loginDetails;
import com.healthcare_server.model.pin;
import com.healthcare_server.model.state;
import com.healthcare_server.respositary.cityRepository;
import com.healthcare_server.respositary.countryRepository;
import com.healthcare_server.respositary.loginRepository;
import com.healthcare_server.respositary.pinRepository;
import com.healthcare_server.respositary.stateRepository;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class adressController {
	@Autowired
    private countryRepository countryRepo;
	@Autowired
    private stateRepository stateRepo;
	@Autowired
    private cityRepository cityRepo;
	@Autowired
    private pinRepository pinRepo;
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/country")
	public List<country>  getCountry() {
		return countryRepo.findAll();
		
	}
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/state")
	public List<state>  getState(int id) {
		System.out.println(id);
		return stateRepo.findAllByCountryId(id);
		
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/city")
	public List<city>  getCity(int id) {
		
		return cityRepo.findAllByStateId(id);
		
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/pin")
	public List<pin>  getPin(int id) {
		return pinRepo.findAllByCityId(id);
		
	}
	
	
}
