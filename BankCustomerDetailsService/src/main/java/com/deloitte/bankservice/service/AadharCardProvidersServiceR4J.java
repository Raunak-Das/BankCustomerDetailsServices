package com.deloitte.bankservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.bankservice.dto.Aadhar;
import com.deloitte.bankservice.repository.AadharCardProxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class AadharCardProvidersServiceR4J {
	
	@Autowired
	AadharCardProxy proxy;
	
	//This method will be executed when CreditScore Service is Up and Running
	@CircuitBreaker(name="aadhar-card-service", fallbackMethod="aadharCardProvidersServiceFallBack")
	public Object getAadharByAadharNo(String aadharCard) {
		System.out.println("Inside Delegate Class FromProxy()");
		Aadhar aadhar = proxy.findAadharByAadharNo(aadharCard);
		return aadhar;
	}
	
	//This method will be executed when CreditScore Service is Down and not Running
	public Object aadharCardProvidersServiceFallBack(String aadharCard, Exception ex) {
		System.out.println("AadharCardProvidersService is Down!!!....Fallback Route Enable...." + ex.getMessage());
		System.out.println("CIRCUIT BREAKER ENABLED!!! No response from AadharCardProvidersService at this point. AadharCardProvidersService will be back shortly...." + new Date());
		Aadhar aadhar = new Aadhar();
		return aadhar;
	}

}
