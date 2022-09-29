package com.deloitte.panservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.panservice.entity.Pan;
import com.deloitte.panservice.exceptions.PanCardNotFoundException;
import com.deloitte.panservice.repository.PanRepository;

@Service
public class PanServiceImpl implements PanService {
	
	@Autowired
	PanRepository repo;

	@Override
	public String addPan(Pan pan) {
		repo.save(pan);
		return pan.getPanNo();
	}

	@Override
	public List<Pan> findAllPan() {
		List<Pan> pan = repo.findAll();
		if(pan.isEmpty()) {
			throw new PanCardNotFoundException();
		}
		return pan;
	}

	@Override
	public Pan findPanByPanNo(String panNo) {
		Pan pan = repo.getPanByPanNo(panNo);
		if(pan==null) {
			return new Pan();
		}
		return pan;
	}
	
	@Override
	public Optional<Pan> findPanByPanNo2(String panNo) {
		// TODO Auto-generated method stub
		Optional<Pan> pan = repo.getPanByPanNo2(panNo);
		if(pan.isEmpty())
			throw new PanCardNotFoundException();
		return pan;
	}

	@Override
	public void delPanByPanNo(Pan pan) {
		Optional<Pan> pan1 = repo.getPanByPanNo2(pan.getPanNo());
		if(pan1.isEmpty()) {
			throw new PanCardNotFoundException();
		}
		repo.delete(pan);
	}
}
