package com.loanapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loanapplication.services.iMailService;


@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*")
public class MailController {

	@Autowired
	private iMailService mailService;

	@PostMapping
	public ResponseEntity<String> sendMail(@RequestParam String email) {
		mailService.sendMail(email);
		return new ResponseEntity<String>("Mail Sent to: " + email, HttpStatus.OK);
	}

}
