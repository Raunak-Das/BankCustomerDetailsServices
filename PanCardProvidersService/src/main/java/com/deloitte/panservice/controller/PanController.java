package com.deloitte.panservice.controller;

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

import com.deloitte.panservice.entity.Pan;
import com.deloitte.panservice.service.PanService;

@RestController
@RequestMapping("/pandetails")
public class PanController {
	
	@Autowired
	PanService service;
	
	@PostMapping
	public ResponseEntity<String> addPan(@RequestBody Pan pan) {
		String panNo = service.addPan(pan);
		return new ResponseEntity<String>("Pan Card Successfully Added with Pan No. " + panNo, HttpStatus.OK);
	}
	
	@GetMapping("/{panNo}")
	public ResponseEntity<Optional<Pan>> findPanByPanNo2(@PathVariable String panNo) {
		Optional<Pan> pan = service.findPanByPanNo2(panNo);
		return new ResponseEntity<Optional<Pan>>(pan, HttpStatus.OK);
	}
	
	@GetMapping("/find/{panNo}")
	public ResponseEntity<Pan> findPanByPanNo(@PathVariable String panNo) {
		Pan pan = service.findPanByPanNo(panNo);
		return new ResponseEntity<Pan>(pan, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Pan>> findAllPan() {
		List<Pan> pan = service.findAllPan();
		return new ResponseEntity<List<Pan>>(pan, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletePanByPanNo(@RequestBody Pan pan) {
		service.delPanByPanNo(pan);
		return new ResponseEntity<String>("Pan Card has been Successfully Deleted", HttpStatus.OK);
	}
}