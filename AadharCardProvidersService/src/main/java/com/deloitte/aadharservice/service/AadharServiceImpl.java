package com.deloitte.aadharservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.aadharservice.entity.Aadhar;
import com.deloitte.aadharservice.exceptions.AadharCardNotFoundException;
import com.deloitte.aadharservice.repository.AadharRepository;

@Service
public class AadharServiceImpl implements AadharService {
	
	@Autowired
	AadharRepository repo;
	
	@Override
	public String addAadhar(Aadhar aadhar) {
		repo.save(aadhar);
		return aadhar.getAadharNo();
	}

	@Override
	public List<Aadhar> findAllAadhar() {
		List<Aadhar> aadhar = repo.findAll();
		if(aadhar.isEmpty()) {
			throw new AadharCardNotFoundException();
		}
		return aadhar;
	}

	@Override
	public Aadhar findAadharByAadharNo(String aadharNo) {
		Aadhar aadhar1 = repo.getAadharByAadharNo(aadharNo);
		if(aadhar1==null) {
			Aadhar aadhar = new Aadhar();
			return aadhar;
			
		} 
		return aadhar1;
	}
	
	@Override
	public Optional<Aadhar> findAadharByAadharNo2(String aadharNo) {
		// TODO Auto-generated method stub
		Optional<Aadhar> aadhar1 = repo.getAadharByAadharNo2(aadharNo);
		if(aadhar1.isEmpty()) {
			throw new AadharCardNotFoundException();
		}
		return aadhar1;
	}
	
	@Override
	public void delAadharByAadharNo(Aadhar aadhar) {
		Optional<Aadhar> aadhar1 = repo.getAadharByAadharNo2(aadhar.getAadharNo());
		if(aadhar1.isEmpty()) {
			throw new AadharCardNotFoundException();
		}
		repo.delete(aadhar);
	}
	
}
