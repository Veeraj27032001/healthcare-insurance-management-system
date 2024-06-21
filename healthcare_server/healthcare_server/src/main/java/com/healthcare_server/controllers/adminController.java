
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

import com.healthcare_server.component.ProviderRegisterReq;
import com.healthcare_server.component.loginRequest;
import com.healthcare_server.model.insuranceProvider;
import com.healthcare_server.model.insuranceProviderPaymentDetail;
import com.healthcare_server.model.loginDetails;
import com.healthcare_server.model.ndc;
import com.healthcare_server.respositary.InsuranceProviderRepository;
import com.healthcare_server.respositary.cptRepository;
import com.healthcare_server.respositary.feesRepository;
import com.healthcare_server.respositary.healthcareProviderRepository;
import com.healthcare_server.respositary.idcRepository;
import com.healthcare_server.respositary.insurancePlanRepository;
import com.healthcare_server.respositary.insuranceProviderPaymentDetailRepository;
import com.healthcare_server.respositary.loginRepository;
import com.healthcare_server.respositary.ndcRepository;

import jakarta.servlet.http.HttpServletResponse;

import com.healthcare_server.model.cpt;
import com.healthcare_server.model.fees;
import com.healthcare_server.model.healthcareProvider;
import com.healthcare_server.model.idc;
import com.healthcare_server.model.insurancePlan;
@RestController
public class adminController {
	@Autowired
	private InsuranceProviderRepository insuranceProviderRepo;
	@Autowired
    private loginRepository loginRepo;
	
	@Autowired
    private healthcareProviderRepository healthcareProviderRepo;

	@Autowired
    private insuranceProviderPaymentDetailRepository insuranceProviderPaymentDetailRepo;
	@Autowired
    private feesRepository feesRepo;
	@Autowired
    private  insurancePlanRepository insurancePlanRepo;
	@Autowired
    private ndcRepository ndcRepo;
	
	@Autowired
    private idcRepository idcRepo;
	@Autowired
    private cptRepository cptRepo;
	
	 insuranceProvider toInsuranceProviderEntity(ProviderRegisterReq dto,int id)
	 {
		 
		
		 insuranceProvider obj = new insuranceProvider();
		 obj.setName(dto.getName());
		 obj.setAdress(dto.getAdress());
		 obj.setCountry(dto.getCountry());
		 obj.setState(dto.getState());
		 obj.setCity(dto.getCity());
		 obj.setPincode(dto.getPincode());
		 obj.setPhoneNumber(dto.getPhoneNumber());
		 obj.setEmail(dto.getEmail());
		 obj.setWebsite(dto.getWebsite());
		 obj.setLicenceNo(dto.getLicenceNo());
		 obj.setId(id);
		return obj;
		 
	 }
	 
	 healthcareProvider toHealthcareProviderEntity(ProviderRegisterReq dto,int id)
	 {
		 healthcareProvider obj = new healthcareProvider();
		 obj.setName(dto.getName());
		 obj.setAddress(dto.getAdress());
		 obj.setCountry(dto.getCountry());
		 obj.setState(dto.getState());
		 obj.setCity(dto.getCity());
		 obj.setPincode(dto.getPincode());
		 obj.setPhoneNumber(dto.getPhoneNumber());
		 obj.setEmail(dto.getEmail());
		 obj.setWebsite(dto.getWebsite());
		 obj.setLicenceNo(dto.getLicenceNo());
		 obj.setId(id);
		return obj;
		 
	 }
	 loginDetails toLoginDetailEntity(ProviderRegisterReq dto)
	 {
		 loginDetails obj=new loginDetails();
		 obj.setEmail(dto.getEmail());
		 obj.setPassword(dto.getPassword());
		 obj.setRole("INSURANCE_PROVIDER");
		return obj;
		 
	 }
	 
	 loginDetails HealthcaretoLoginDetailEntity(ProviderRegisterReq dto)
	 {
		 loginDetails obj=new loginDetails();
		 obj.setEmail(dto.getEmail());
		 obj.setPassword(dto.getPassword());
		 obj.setRole("HEALTHCARE_PROVIDER");
		return obj;
		 
	 }
	 
	 
	@CrossOrigin("http://localhost:3000")
	@PostMapping("registerInsuranceProvider")
	public  ResponseEntity<Object> register(@RequestBody ProviderRegisterReq req)
	{
	 try {
		 insuranceProviderPaymentDetail insuranceProviderPaymentDetailObj=new insuranceProviderPaymentDetail();
		 Optional<loginDetails> data=loginRepo.findByEmail(req.getEmail());
		 if(data.isPresent())
		 {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("insurance provider with this email already Exists");	 
		 }
		 else {
		  loginDetails savedlogin=loginRepo.save(toLoginDetailEntity(req));
			
		  insuranceProviderRepo.save( toInsuranceProviderEntity(req,savedlogin.getId()));
		  insuranceProviderPaymentDetailObj.setId(savedlogin.getId());
		  insuranceProviderPaymentDetailRepo.save(insuranceProviderPaymentDetailObj);
		  return ResponseEntity.ok().body("sucess");
		   }
	 	}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
		}
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("editInsuranceProvider")
	public  ResponseEntity<Object> editInsuranceProvider(@RequestBody insuranceProvider req)
	{

		 try {
			  
			  insuranceProviderRepo.save(req);
			  return ResponseEntity.ok().body("sucess");
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
	  }
	
	}
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("getInsuranceProviders")
	public List<insuranceProvider> getListOfInsuranceProvider()
	{
	 return	insuranceProviderRepo.findAll();
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("deleteInsuranceProvider")
	public  ResponseEntity<Object> deleteInsuranceProvider(int id)
	{
		
		try {
			
			 insuranceProvider insuranceobj = new insuranceProvider();
			
			 loginDetails loginobj=new loginDetails();
			 insuranceobj.setId(id); 
			 loginobj.setId(id);
			 Optional<insuranceProvider> insuranceobj2 = insuranceProviderRepo.findById((long)id);
			 
			  List<insurancePlan> planobj=insurancePlanRepo.findAllByInsuranceProvider(id);
			  for(insurancePlan plan:planobj )
			  {
				  plan.setStatus(false);
				  insurancePlanRepo.save(plan);
			  }
			  
			  insuranceProviderRepo.delete(insuranceobj);
			  loginRepo.delete(loginobj);
			  
			  
			  return ResponseEntity.ok().body("sucess");
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to delete");	
			}
	}
	
	
	 
		@CrossOrigin("http://localhost:3000")
		@PostMapping("registerHealthcareProvider")
		public  ResponseEntity<Object> registerHealthcareProvider(@RequestBody ProviderRegisterReq req)
		{
		 try {
			 
			 Optional<loginDetails> data=loginRepo.findByEmail(req.getEmail());
			 if(data.isPresent())
			 {
				 return ResponseEntity.status(HttpStatus.CONFLICT).body("healthcare provider with this email already Exists");	 
			 }
			 else {
			  loginDetails savedlogin=loginRepo.save(HealthcaretoLoginDetailEntity(req));
				
			  healthcareProviderRepo.save( toHealthcareProviderEntity(req,savedlogin.getId()));
			  fees feesObj=new fees();
			  
			  feesObj.setCounsultanceFees(0);
			  feesObj.setId(savedlogin.getId());
			 
			  feesRepo.save(feesObj);
			  return ResponseEntity.ok().body("sucess");
			 }
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		
		@CrossOrigin("http://localhost:3000")
		@RequestMapping("deleteHealthcareProvider")
		public  ResponseEntity<Object> deleteHealthcareProvider(int id)
		{
			
			try {
				
				 healthcareProvider healthcareobj = new healthcareProvider();
				 loginDetails loginobj=new loginDetails();
				 healthcareobj.setId(id);
				 loginobj.setId(id);
                 fees feesObj=new fees();
				  
				  feesObj.setId(id);
				  feesRepo.delete(feesObj);
				  healthcareProviderRepo.delete(healthcareobj);
				  loginRepo.delete(loginobj);
				  return ResponseEntity.ok().body("sucess");
				}
				catch(Exception e)
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to delete");	
				}
		}
		
		@CrossOrigin("http://localhost:3000")
		@RequestMapping("getHealthcareProviders")
		public List<healthcareProvider> getListOfHealthcareProvider()
		{
			
			 return	 healthcareProviderRepo.findAll();
		
		}
		
		
		@CrossOrigin("http://localhost:3000")
		@PostMapping("editHealthcareProvider")
		public  ResponseEntity<Object> editHealthProvider(@RequestBody healthcareProvider req)
		{

			 try {
				  
				  healthcareProviderRepo.save(req);
				  return ResponseEntity.ok().body("sucess");
				}
				catch(Exception e)
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
		  }
		
		}
		
		
		
		
		@CrossOrigin("http://localhost:3000")
		@PostMapping("addNdc")
		public  ResponseEntity<Object> addNdc(@RequestBody ndc req)
		{
		 try {
			 Optional<ndc> data=ndcRepo.findByCode(req.getCode());
			 if(data.isPresent())
			 {
				 return ResponseEntity.status(HttpStatus.CONFLICT).body("code already Exists");	 
			 }
			 else {
				  ndcRepo.save(req);
				  return ResponseEntity.ok().body("sucess");
			 }
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		@CrossOrigin("http://localhost:3000")
		@PostMapping("addIdc")
		public  ResponseEntity<Object> addIdc(@RequestBody idc req)
		{
		 try {
			 
				 Optional<idc> data=idcRepo.findByCode(req.getCode());
				 if(data.isPresent())
				 {
					 return ResponseEntity.status(HttpStatus.CONFLICT).body("code already Exists");	 
				 }
				 else {
					 idcRepo.save(req);
					 return ResponseEntity.ok().body("sucess");
				 }
			 }
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		@CrossOrigin("http://localhost:3000")
		@PostMapping("addCpt")
		public  ResponseEntity<Object> addCpt(@RequestBody cpt req)
		{
		 try {
			 Optional<cpt> data=cptRepo.findByCode(req.getCode());
			 if(data.isPresent())
			 {
				 return ResponseEntity.status(HttpStatus.CONFLICT).body("code already Exists");	 
			 }
			 else {
			  cptRepo.save(req);
			  return ResponseEntity.ok().body("sucess");
			 }
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		
		

		@CrossOrigin("http://localhost:3000")
		@RequestMapping("getNdc")
		public List<ndc> getNdc()
		{
		 return	ndcRepo.findAll();
		}
		
		@CrossOrigin("http://localhost:3000")
		@RequestMapping("getIdc")
		public List<idc> getIdc()
		{
		 return	idcRepo.findAll();
		}
		
		
		@CrossOrigin("http://localhost:3000")
		@RequestMapping("getCpt")
		public List<cpt> getCpt()
		{
		 return	cptRepo.findAll();
		}
		
		
		@CrossOrigin("http://localhost:3000")
		@RequestMapping("deleteNdc")
		public  ResponseEntity<Object> deleteNdc(int id)
		{
			
			try {
				
                 ndc ndcObj=new ndc();
				 ndcObj.setId(id);
				 ndcRepo.delete(ndcObj);
				 
				  return ResponseEntity.ok().body("sucess");
				}
				catch(Exception e)
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to delete");	
				}
		}
		

		@CrossOrigin("http://localhost:3000")
		@RequestMapping("deleteIdc")
		public  ResponseEntity<Object> deleteIdc(int id)
		{
			
			try {
				
                 idc idcObj=new idc();
				 idcObj.setId(id);
				 idcRepo.delete(idcObj);
				 
				  return ResponseEntity.ok().body("sucess");
				}
				catch(Exception e)
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to delete");	
				}
		}
		

		@CrossOrigin("http://localhost:3000")
		@RequestMapping("deleteCpt")
		public  ResponseEntity<Object> deleteCpt(int id)
		{
			
			try {
				
                 cpt cptObj=new cpt();
				 cptObj.setId(id);
				 cptRepo.delete(cptObj);
				 
				  return ResponseEntity.ok().body("sucess");
				}
				catch(Exception e)
				{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to delete");	
				}
		}
		@CrossOrigin("http://localhost:3000")
		@PostMapping("editNdc")
		public  ResponseEntity<Object> editNdc(@RequestBody ndc req)
		{
		 try {
			 
			  ndcRepo.save(req);
			  return ResponseEntity.ok().body("sucess");
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		@CrossOrigin("http://localhost:3000")
		@PostMapping("editIdc")
		public  ResponseEntity<Object> editIdc(@RequestBody idc req)
		{
		 try {
			 
			  idcRepo.save(req);
			  return ResponseEntity.ok().body("sucess");
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		@CrossOrigin("http://localhost:3000")
		@PostMapping("editCpt")
		public  ResponseEntity<Object> editCpt(@RequestBody cpt req)
		{
		 try {
			 
			  cptRepo.save(req);
			  return ResponseEntity.ok().body("sucess");
			}
			catch(Exception e)
			{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to register");	
			}
		}
		
		
}
