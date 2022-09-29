package com.deloitte.aadharservice.service;

import java.util.List;
import java.util.Optional;

import com.deloitte.aadharservice.entity.Aadhar;

public interface AadharService {
	
	String addAadhar(Aadhar aadhar);
	List<Aadhar> findAllAadhar();
	void delAadharByAadharNo(Aadhar aadhar);
	Aadhar findAadharByAadharNo(String aadharNo);
	Optional<Aadhar> findAadharByAadharNo2(String aadharNo); 
}
