package com.deloitte.bankservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.bankservice.dto.Aadhar;
import com.deloitte.bankservice.dto.Pan;
import com.deloitte.bankservice.entity.Customer;
import com.deloitte.bankservice.exceptions.AadharCardProvidersServiceDownException;
import com.deloitte.bankservice.exceptions.AadharCardProvidersServiceDownException2;
import com.deloitte.bankservice.exceptions.AccountNotFoundException;
import com.deloitte.bankservice.exceptions.KYCFailedException;
import com.deloitte.bankservice.exceptions.PanCardProvidersServiceDownException;
import com.deloitte.bankservice.exceptions.PanCardProvidersServiceDownException2;
import com.deloitte.bankservice.exceptions.PanValidationFailedException;
import com.deloitte.bankservice.repository.AadharCardProxy;
import com.deloitte.bankservice.repository.BankRepository;
import com.deloitte.bankservice.repository.PanCardProxy;


@Service
public class BankServiceImpl implements BankService {
	
	private int accountNo=10001;
	
	@Autowired
	BankRepository repo;
	
	@Autowired
	AadharCardProxy proxy;
	
	@Autowired
	PanCardProxy proxy2;
	
	@Autowired
	AadharCardProvidersServiceR4J r4j;
	
	@Autowired
	PanCardProvidersServiceR4J r4j2;

	@Override
	public int addCustomer(Customer customer) {
		
		Optional<Integer> accNo = repo.getMaxAccountNo();
		if(accNo.isEmpty())
			customer.setAccountNo(accountNo);
		else {
			accountNo = accNo.get().intValue();
			accountNo++;
			customer.setAccountNo(accountNo);
		}
		
		String aadharNo = customer.getAadharNo();
		
		customer.setAadharRemarks("KYC PENDING");
		customer.setPanRemarks("PAN VALIDATION PENDING");
		
		repo.save(customer);
		
		if(aadharNo!=null) {
			
			//Using Feign Client
			//Aadhar aadhar = proxy.findAadharByAadharNo(aadharNo);
			
			//Using CircuitBreaker with Resilience4j
			Aadhar aadhar = (Aadhar) r4j.getAadharByAadharNo(aadharNo);
			
			if(aadhar.getAadharNo()==null) {
				throw new AadharCardProvidersServiceDownException2();
			}
			
			if(aadhar.getAadharNo()==null) {
				
				customer.setAadharRemarks("KYC FAILED");
				repo.save(customer);
				throw new KYCFailedException();
			}
				
			customer.setAadharRemarks("KYC APPROVED");

		}
		
		String panNo = customer.getPancardNo();
		
		if(panNo!=null) {
			
			//Using Feign Client
			//Pan pan = proxy2.findPanByPanNo(panNo);
			
			//Using CircuitBreaker with Resilience4j
			Pan pan = (Pan) r4j2.getPanByPanNo(panNo);
			
			if(pan.getPanNo()==null) {
				throw new PanCardProvidersServiceDownException2();
			}
			
			if(pan.getPanNo()==null) {
				customer.setPanRemarks("PAN VALIDATION FAILED");
				repo.save(customer);
				throw new PanValidationFailedException();
			}
			
			customer.setPanRemarks("PAN VALIDATION APPROVED");
		}
		
		repo.save(customer);
		return customer.getAccountNo();
	}
			
			

	@Override
	public List<Customer> findAllCustomers() {
		List<Customer> cust = repo.findAll();
		if(cust.isEmpty()) {
			throw new AccountNotFoundException();
		}
		return cust;
	}

	@Override
	public Optional<Customer> findCustomerByAccNo(int accountNo) {
		Optional<Customer> cust = repo.findById(accountNo);
		if(cust.isEmpty()) {
			throw new AccountNotFoundException();
		}
		return cust;
	}

	@Override
	public void delCustomerByCode(int accountNo) {
		Optional<Customer> cust = repo.findById(accountNo);
		if(cust.isEmpty()) {
			throw new AccountNotFoundException();
		}
		repo.deleteById(accountNo);
	}

	@Override
	public void knowYourCustomer(int accountNo, String aadharNo) {
		
		Customer cust = repo.getCustomerDetailsByAccountNo(accountNo);
		if(cust==null) {
			throw new AccountNotFoundException();
		}
		
		cust.setAadharNo(aadharNo);
		
		cust.setAadharRemarks("KYC PENDING");
		
		repo.save(cust);
		
		//Using Feign Client
		//Aadhar aadhar = proxy.findAadharByAadharNo(aadharNo);
		 
		//Using CircuitBreaker with Resilience4j
		Aadhar aadhar = (Aadhar) r4j.getAadharByAadharNo(aadharNo);
		
		if(aadhar.getAadharNo()==null) {
			throw new AadharCardProvidersServiceDownException();
		}
		
		if(aadhar.getAadharNo()==null) {
			cust.setAadharRemarks("KYC FAILED");
			repo.save(cust);
			throw new KYCFailedException();
		}
		
		cust.setAadharRemarks("KYC APPROVED");
		
		repo.save(cust);
	}

	@Override
	public void validatePanCard(int accountNo, String panNo) {

		Customer cust = repo.getCustomerDetailsByAccountNo(accountNo);
		if(cust==null) {
			throw new AccountNotFoundException();
		}
		
		cust.setPancardNo(panNo);
		
		cust.setPanRemarks("PAN VALIDATION PENDING");
		
		repo.save(cust);
		
		//Using Feign Client
		//Pan pan = proxy2.findPanByPanNo(panNo);
		
		//Using CircuitBreaker with Resilience4j
		Pan pan = (Pan) r4j2.getPanByPanNo(panNo);
		
		if(pan.getPanNo()==null) {
			throw new PanCardProvidersServiceDownException();
		}
		
		if(pan.getPanNo()==null) {
			cust.setPanRemarks("PAN VALIDATION FAILED");
			repo.save(cust);
			throw new PanValidationFailedException();
			
		}

		cust.setPanRemarks("PAN VALIDATION APPROVED");
		
		repo.save(cust);
		
	}
}