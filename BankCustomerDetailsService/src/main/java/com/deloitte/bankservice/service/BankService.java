package com.deloitte.bankservice.service;

import java.util.List;
import java.util.Optional;

import com.deloitte.bankservice.entity.Customer;


public interface BankService {
	
	int addCustomer(Customer customer);
	List<Customer> findAllCustomers();
	Optional<Customer> findCustomerByAccNo(int accountNo);
	void delCustomerByCode(int accountNo);
	void knowYourCustomer(int accountNo, String aadharNo);
	void validatePanCard(int accountNo, String panNo);
}
