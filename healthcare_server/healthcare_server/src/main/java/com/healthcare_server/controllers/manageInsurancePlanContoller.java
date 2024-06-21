
package com.healthcare_server.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import org.apache.catalina.connector.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare_server.component.insurancePlanRequest;
import com.healthcare_server.component.loginRequest;
import com.healthcare_server.component.renewRequest;
import com.healthcare_server.model.insurancePlan;
import com.healthcare_server.model.insuranceProviderPaymentDetail;
import com.healthcare_server.model.insuranceRenewal;
import com.healthcare_server.model.patientInsurancePolicy;
import com.healthcare_server.model.planCptCoverage;
import com.healthcare_server.model.planIdcCoverage;
import com.healthcare_server.model.planNdcCoverage;
import com.healthcare_server.respositary.insurancePlanRepository;
import com.healthcare_server.respositary.insuranceProviderPaymentDetailRepository;
import com.healthcare_server.respositary.insuranceRenewalRepository;
import com.healthcare_server.respositary.loginRepository;
import com.healthcare_server.respositary.patientInsurancePolicyRepository;
import com.healthcare_server.respositary.planCpCoverageRepository;
import com.healthcare_server.respositary.planIdcCovereageRepository;
import com.healthcare_server.respositary.planNdcCoverageRepository;
import com.healthcare_server.service.policy_payment_date;

import java.time.format.DateTimeFormatter;
import jakarta.servlet.http.HttpServletResponse;
@RestController
public class manageInsurancePlanContoller {
	private static Logger logger = LoggerFactory.getLogger(policy_payment_date.class);
	@Autowired
    private  insurancePlanRepository insurancePlanRepo;
	@Autowired
    private  planCpCoverageRepository cptCoverageRepo;
	@Autowired
    private  planIdcCovereageRepository idcCoverageRepo;
	@Autowired
    private  planNdcCoverageRepository ndcCoverageRepo;
	@Autowired
	private  patientInsurancePolicyRepository  patientInsurancePolicyRepo; 
	@Autowired
	private insuranceRenewalRepository insuranceRenewalRepo;
	@Autowired
    private insuranceProviderPaymentDetailRepository insuranceProviderPaymentDetailRepo;
	
	public insurancePlan requestToInsurancePlanEntity(insurancePlanRequest req)
	{
		insurancePlan insurance=new insurancePlan();
		insurance.setInsuranceProvider(req.getInsuranceProvider());
		
		insurance.setPlanName(req.getPlanName());
		insurance.setCoverageLimit(req.getCoverageLimit());
		insurance.setFrequency(req.getFrequency());
		insurance.setPremium(req.getPremium());
		insurance.setStatus(req.isStatus());
		return insurance;
	}
	
	public List<planCptCoverage> requestToCptCoverageEntity(insurancePlanRequest req,int id)
	{
		
		List<planCptCoverage>  listCpt=new ArrayList<planCptCoverage>() ;
		int []data=req.getCptId();
		for(int d:data )
		{
		planCptCoverage cptcoverage=new planCptCoverage();
		cptcoverage.setCptId(d);
		cptcoverage.setPlanId(id);
		listCpt.add(cptcoverage);
		}
		return listCpt;
	}
	
	public List<planNdcCoverage> requestToNdcCoverageEntity(insurancePlanRequest req,int id)
	{
	
		List<planNdcCoverage>  listNdc=new ArrayList<planNdcCoverage>() ;
		int []data=req.getNdcId();
		for(int d:data )
		{
		planNdcCoverage ndccoverage=new planNdcCoverage();
		ndccoverage.setNdcId(d);
		ndccoverage.setPlanId(id);
		listNdc.add(ndccoverage);
		}
		return listNdc;
	}
	
	
	public List<planIdcCoverage> requestToIdcCoverageEntity(insurancePlanRequest req,int id)
	{
		
		List<planIdcCoverage>  listIdc=new ArrayList<planIdcCoverage>() ;
		
		int []data=req.getIdcId();
		for(int d:data )
		{
		planIdcCoverage idccoverage=new planIdcCoverage();
		
		idccoverage.setIdcId(d);
		idccoverage.setPlanId(id);
		listIdc.add(idccoverage);
		}
		return listIdc;
	}
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/insurancePlan")
	public List<insurancePlan> getinsurancePlan(boolean status,int id) throws IOException{
		return insurancePlanRepo.findAllByStatusAndInsuranceProvider(status,id);
	}
	
	
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/addInsurancePlan")
	public ResponseEntity<Object> addInsurancePlan(@RequestBody insurancePlanRequest req){
	
		try {
			
			
			insurancePlan insurance=insurancePlanRepo.save(requestToInsurancePlanEntity(req));
			ndcCoverageRepo.saveAll(requestToNdcCoverageEntity(req,insurance.getId()));
			List<planIdcCoverage> list=requestToIdcCoverageEntity(req,insurance.getId());
			idcCoverageRepo.saveAll(list);
			cptCoverageRepo.saveAll(requestToCptCoverageEntity(req,insurance.getId()));
			return ResponseEntity.ok().body("sucessfully added plan");
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("faieled to add insurance plan");	
		}
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/activateInsurancePlan")
	public ResponseEntity<Object> activateInsurancePlan(boolean status, int id){
	
		try {
			Optional<insurancePlan> plan=insurancePlanRepo.findById((long) id);
			if(plan.isPresent())
			{
				insurancePlan insurance=plan.get();
				insurance.setId(id);
				insurance.setStatus(status);
				insurancePlanRepo.save(insurance);
				return ResponseEntity.ok().body("sucessfully updated  insurance plan status");	
			
			}
			
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed to update  insurance plan status");	
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed to update  insurance plan status");	
		
	
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getALLInsurancePlan")
	public List<insurancePlan> findAllPlan()
	{
		return insurancePlanRepo.findAllByStatus(true);
		}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getALLCptCoverage")
	public List<planCptCoverage> getCptCoverage()
	{
		return cptCoverageRepo.findAll();
		}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getALLNdcCoverage")
	public List<planNdcCoverage> getNdcCoverage()
	{
		return ndcCoverageRepo.findAll();
		}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getALLIdcCoverage")
	public List<planIdcCoverage> getIdcCoverage()
	{
		return idcCoverageRepo.findAll();
	}

	@CrossOrigin("http://localhost:3000")
	@PostMapping("/buyInsurancePolicy")
	public <patientInsurnacePolicy> ResponseEntity<Object> buyInsurancePolicy(@RequestBody patientInsurancePolicy req)
	{ 
		
	  String policy_no ;
	  insuranceRenewal insuranceRenewalObj=new insuranceRenewal();
		 do{
			 Random random = new Random();
		
		      
		    policy_no =String.valueOf(Math.abs(1000000000L + random.nextLong() % 9000000000L));
		   
		     Optional< patientInsurancePolicy> patientPolicyData=patientInsurancePolicyRepo.findAllByPolicyNo(policy_no);
		     if(!patientPolicyData.isPresent())
		     {
		    	 req.setPolicyNo(policy_no);
		    	 insuranceRenewalObj.setPatientPolicyNo(policy_no);
		    	 break;
		     }
		 }while(true);
		 List<insurancePlan> plan=insurancePlanRepo.findAllById( (long)req.getPlanId());
		 logger.info(plan.toString());
		 
		if( !plan.isEmpty())
		{	
		 String frequency=plan.get(0).getFrequency()	;
		 if(frequency.equals("MONTHLY"))
		 {
			  Date date=req.getBroughtDate();
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    cal.add(Calendar.DAY_OF_MONTH, 30);
			    Date newDate=cal.getTime();
	         insuranceRenewalObj.setPaymentDate(newDate);
		 }
		 else if(frequency.equals("ANUALLY")){
			 Date date=req.getBroughtDate();
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    cal.add(Calendar.DAY_OF_MONTH, 365);
			    Date newDate=cal.getTime();
	           insuranceRenewalObj.setPaymentDate(newDate);
		 }
		}
		
		  
         insuranceRenewalObj.setPaymentStatus(false);
         insuranceRenewalObj.setRenewalShow(false);
         patientInsurancePolicyRepo.save(req);
	     insuranceRenewalRepo.save( insuranceRenewalObj);
	     
		return  ResponseEntity.ok().body("sucessfully brought insurnace paln ");
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getPatientPolicy")
	public List<patientInsurancePolicy> getPatientPolicy(int id)
	{
		return patientInsurancePolicyRepo.findAllByPatientId(id);
	}
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getPatientRenewalPolicy")
	public List<insuranceRenewal> getPatientRenewalPolicy(String policyno)
	{
		return insuranceRenewalRepo.findAllByPatientPolicyNoAndRenewalShowAndPaymentStatus(policyno,true,false);
	}
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getInsuranceplanById")
	public List<insurancePlan> getPatientRenewalPolicy(int id)
	{
		return insurancePlanRepo.findAllById(id);
	}
	
//	@CrossOrigin("http://localhost:3000")
//	@RequestMapping("/renew")
//	public ResponseEntity<Object> renew(int renewId,String policyNo,int amt)
//	{
//		Optional<insuranceRenewal> renewData=insuranceRenewalRepo.findById(renewId);
//		Optional<patientInsurancePolicy> insurnacePolicy=patientInsurancePolicyRepo.findAllByPolicyNo(policyNo);
//		insuranceRenewal newRenewal=null;
//		patientInsurancePolicy newPolicy=null;
//		if(!renewData.isEmpty())
//		{
//			
//			newRenewal=renewData.get();
//			newRenewal.setPaymentStatus(true);
//			newRenewal.setRenewalShow(false);
//			insuranceRenewalRepo.save(newRenewal);
//		}
//		
//		if(!insurnacePolicy.isEmpty())
//		{
//			newPolicy=insurnacePolicy.get();
//			newPolicy.setPaidAmt(newPolicy.getPaidAmt()+amt);
//			
//			patientInsurancePolicyRepo.save(newPolicy);	
//		}
//		
//		
//		
//		return  ResponseEntity.ok().body(insurnacePolicy.get());
//	}
	
	
	
	
	

	@CrossOrigin("http://localhost:3000")
	@PostMapping("/addInsurancePaymentDetail")
	public ResponseEntity<Object> addInsurancePaymentDetail(@RequestBody insuranceProviderPaymentDetail req){
	
		try {
			
			insuranceProviderPaymentDetailRepo.save(req);
			return ResponseEntity.ok().body("sucessfully added paymentDetail");
			
		}catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("faieled to add insurancepayer paymet detail");	
		}
	}
	
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("/getInsurancePayerPaymentDetail")
	public  insuranceProviderPaymentDetail getInsuranceProviderPaymentDetail(int id){
		Optional<insuranceProviderPaymentDetail> data=insuranceProviderPaymentDetailRepo.findById((long)id);
		if(data.isPresent()) {
		return (data.get());
		}
		else {
			return new insuranceProviderPaymentDetail();
		}
		
	}
	
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/renew")
	public ResponseEntity<Object> renew(@RequestBody renewRequest jsonObj)
	{
	 try {
		Optional<insuranceRenewal> renewData=insuranceRenewalRepo.findById(jsonObj.getRenewId());
		Optional<patientInsurancePolicy> insurnacePolicy=patientInsurancePolicyRepo.findAllByPolicyNo( jsonObj.getPolicyNo());
		insuranceRenewal newRenewal=null;
		patientInsurancePolicy newPolicy=null;
		if(!renewData.isEmpty())
		{
			ObjectMapper ObjectWrapperObj=new ObjectMapper();
			newRenewal=renewData.get();
			newRenewal.setPaymentStatus(true);
			newRenewal.setRenewalShow(false);
			newRenewal.setPaymentDetail( ObjectWrapperObj.writeValueAsString( jsonObj.getResponse()));
			insuranceRenewalRepo.save(newRenewal);
		}
		
		if(!insurnacePolicy.isEmpty())
		{
			newPolicy=insurnacePolicy.get();
			newPolicy.setPaidAmt((int) (newPolicy.getPaidAmt()+jsonObj.getAmt()));
			
			patientInsurancePolicyRepo.save(newPolicy);	
		}
		
		
		
		return  ResponseEntity.ok().body("success");
	 }
	 catch(Exception e)
	 {
		 return  ResponseEntity.status(500).body(e);
	 }
	}
	
}
