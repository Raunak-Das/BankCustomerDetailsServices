package com.deloitte.bankservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.bankservice.entity.Customer;


@Repository
public interface BankRepository extends JpaRepository<Customer, Integer> {
	
	@Query(value="select max(accountNo) from Customer")
	Optional<Integer>  getMaxAccountNo();
	
	@Query(value="select cust from Customer cust where cust.accountNo=?1")
	Customer getCustomerDetailsByAccountNo(int accountNo);
} 