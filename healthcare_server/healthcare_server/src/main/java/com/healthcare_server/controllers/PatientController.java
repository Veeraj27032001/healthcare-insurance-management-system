package com.healthcare_server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare_server.component.ProviderRegisterReq;
import com.healthcare_server.component.patientRegistrationRequest;
import com.healthcare_server.model.loginDetails;
import com.healthcare_server.model.patientPersonalDetails;
import com.healthcare_server.respositary.loginRepository;
import com.healthcare_server.respositary.patientPersonalDetailRepository;

@RestController
public class PatientController {
	
	@Autowired
    private loginRepository loginRepo;
	
	@Autowired
    private patientPersonalDetailRepository patientPersonalDetailsRepo;
	
	
	
	loginDetails toLoginDetailEntity(patientRegistrationRequest dto)
	 {
		 loginDetails obj=new loginDetails();
		 obj.setEmail(dto.getEmail());
		 obj.setPassword(dto.getPassword());
		 obj.setRole("PATIENT");
		return obj;
		 
	 }
	
	patientPersonalDetails toPatientPersonalDetailsEntity( patientRegistrationRequest dto,int id)
	 {
		patientPersonalDetails obj=new patientPersonalDetails();
		
		obj.setId(id);
		obj.setFirstName(dto.getFirstName());
		obj.setLastName(dto.getLastName());
		obj.setAddress(dto.getAddress());
		 obj.setCountry(dto.getCountry());
		 obj.setState(dto.getState());
		 obj.setCity(dto.getCity());
		 obj.setPin(dto.getPin());
		 obj.setPhoneNumber(dto.getPhoneNumber());
		 obj.setEmail(dto.getEmail());
		 obj.setDob(dto.getDob());
		 obj.setAge(dto.getAge());
		 obj.setGender(dto.getGender());
		return obj;
		
	 }
	
	
	 @CrossOrigin("http://localhost:3000")
	 @PostMapping("/registerPatient")
	 public ResponseEntity <Object>  RegisterPatient(@RequestBody patientRegistrationRequest req)
	 {
		 
		 try {
			 Optional<loginDetails> data=loginRepo.findByEmail(req.getEmail());
			 if(data.isPresent())
			 {
				 return ResponseEntity.status(HttpStatus.CONFLICT).body("patient with this email already Exists");	 
			 }
			 else {
     		  loginDetails savedlogin=loginRepo.save(toLoginDetailEntity(req));
				
     		  patientPersonalDetailsRepo.save( toPatientPersonalDetailsEntity(req,savedlogin.getId()));
			  return ResponseEntity.ok().body("sucessfully registered");
			 }
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed to register");	
		 
			}
	 	}
	 @CrossOrigin("http://localhost:3000")
	 @RequestMapping("/getPatientInfo")
	 public ResponseEntity <Object>  getPatientInfo(int id)
	 {
		Optional<patientPersonalDetails> data= patientPersonalDetailsRepo.findById((long)id);
		if(data.isPresent())
		{
			return ResponseEntity.ok().body(data.get());
			
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed to get patient personal data");	
		}
		
	 } 
	 
}
