
package com.healthcare_server.controllers;

import java.io.IOException;
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
import com.healthcare_server.model.loginDetails;
import com.healthcare_server.respositary.loginRepository;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class AuthenticationController {
	@Autowired
    private loginRepository loginRepo;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody loginRequest req) throws IOException{
		Optional<loginDetails> userDetail=loginRepo.findByEmailAndPassword(req.getEmail(), req.getPassword());
	    if(userDetail.isPresent())
	    {
		   loginDetails user =userDetail.get();
		   user.setPassword(null);
		    return ResponseEntity.ok().body(user);
	    }
	    else {
	    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");	
	    }
	}
	
	
}
