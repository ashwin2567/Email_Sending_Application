package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;

@RestController
@CrossOrigin
public class MainController {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to mumbai";
	}
	
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		System.out.println(request);
		//boolean res = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		boolean res = this.emailService.sendEmailWithAttachment(request.getSubject(), request.getMessage(), request.getTo());
		if(res) {
			System.out.println("Sent successfulyy");
			return ResponseEntity.ok(new EmailResponse("Email is sent successfully."));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Some error occured"));
		
	}
}
