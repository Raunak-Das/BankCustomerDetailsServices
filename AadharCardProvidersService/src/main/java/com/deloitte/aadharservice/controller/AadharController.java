package com.deloitte.aadharservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.aadharservice.entity.Aadhar;
import com.deloitte.aadharservice.service.AadharService;

@RestController
@RequestMapping("/aadhardetails")
public class AadharController {
	
	@Autowired
	AadharService service;
	
	@PostMapping
	public ResponseEntity<String> addAadhar(@RequestBody Aadhar aadhar) {
		String aadharNo = service.addAadhar(aadhar);
		return new ResponseEntity<String>("Aadhar Card Successfully Added with Aadhar No. " + aadharNo, HttpStatus.OK);
	}
	
	@GetMapping("/find/{aadharNo}")
	public ResponseEntity<Aadhar> findAadharByAadharNo(@PathVariable String aadharNo) {
		Aadhar aadhar = service.findAadharByAadharNo(aadharNo);
		return new ResponseEntity<Aadhar>(aadhar, HttpStatus.OK);
	}
	
	@GetMapping("/{aadharNo}")
	public ResponseEntity<Optional<Aadhar>> findAadharByAadharNo2(@PathVariable String aadharNo) {
		Optional<Aadhar> aadhar = service.findAadharByAadharNo2(aadharNo);
		return new ResponseEntity<Optional<Aadhar>>(aadhar, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Aadhar>> findAllAadhar() {
		List<Aadhar> aadhar = service.findAllAadhar();
		return new ResponseEntity<List<Aadhar>>(aadhar, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteAadharByAadharNo(@RequestBody Aadhar aadhar) {
		service.delAadharByAadharNo(aadhar);
		return new ResponseEntity<String>("Aadhar Card has been Successfully Deleted", HttpStatus.OK);
	}	

}