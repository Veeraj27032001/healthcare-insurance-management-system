package com.healthcare_server.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare_server.model.adminFeatures;
import com.healthcare_server.model.banners;
import com.healthcare_server.model.insuranceProviderPaymentDetail;
import com.healthcare_server.model.insuranceproviderFeatures;
import com.healthcare_server.model.patientFeatures;
import com.healthcare_server.respositary.adminFeaturesRepository;
import com.healthcare_server.respositary.bannersRepository;
import com.healthcare_server.respositary.insuranceProviderFeaturesRepository;
import com.healthcare_server.respositary.insuranceProviderPaymentDetailRepository;
import com.healthcare_server.respositary.patientFeaturesRepository;
import com.healthcare_server.respositary.siteDetailsRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class paymentGateWayController {
	

	@Autowired
    private insuranceProviderPaymentDetailRepository insuranceProviderPaymentDetailRepo;
	
	
	@CrossOrigin("http://localhost:3000")
	@RequestMapping("generateOrder")
	ResponseEntity<Object> generateOrder(int id,double amt) {
		
		try {
			
		Optional<insuranceProviderPaymentDetail> paymentdata=insuranceProviderPaymentDetailRepo.findById((long)id);
		System.out.println(amt);
		if(paymentdata.isPresent())
		{
		RazorpayClient razorpay = new RazorpayClient(paymentdata.get().getKeyId(), paymentdata.get().getSecretKey());

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",amt*100);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receipt#1");
		JSONObject notes = new JSONObject();
		notes.put("notes_key_1","renewal of insurance");
		orderRequest.put("notes",notes);
		Order order = razorpay.orders.create(orderRequest);
		 order.toJson();
		
		
		return ResponseEntity.ok().body(order.toJson().get("id"));
		}
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to generate order Id");	
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fialed to generate order Id");
		
	
	}

}
