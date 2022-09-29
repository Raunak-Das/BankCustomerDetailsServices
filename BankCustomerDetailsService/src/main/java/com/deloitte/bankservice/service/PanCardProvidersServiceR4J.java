package com.deloitte.bankservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.bankservice.dto.Pan;
import com.deloitte.bankservice.repository.PanCardProxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class PanCardProvidersServiceR4J {
	
	@Autowired
	PanCardProxy proxy;
	
	//This method will be executed when CreditScore Service is Up and Running
	@CircuitBreaker(name="pan-card-service", fallbackMethod="panCardProvidersServiceFallBack")
	public Object getPanByPanNo(String panCard) {
		System.out.println("Inside Delegate Class FromProxy()");
		Pan pan = proxy.findPanByPanNo(panCard);
		return pan;
	}
	
	//This method will be executed when CreditScore Service is Down and not Running
	public Object panCardProvidersServiceFallBack(String panCard, Exception ex) {
		System.out.println("PanCardProvidersService is Down!!!....Fallback Route Enable...." + ex.getMessage());
		System.out.println("CIRCUIT BREAKER ENABLED!!! No response from PanCardProvidersService at this point. PanCardProvidersService will be back shortly...." + new Date());
		Pan pan = new Pan();
		return pan;
	}

}
