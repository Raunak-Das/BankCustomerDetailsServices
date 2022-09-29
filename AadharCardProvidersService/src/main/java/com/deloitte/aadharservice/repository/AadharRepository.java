package com.deloitte.aadharservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.aadharservice.entity.Aadhar;


@Repository
public interface AadharRepository extends JpaRepository<Aadhar, String> {
	
	@Query(value="select aadhar from Aadhar aadhar where aadhar.aadharNo=?1")
	Aadhar getAadharByAadharNo(String aadharNo);
	
	@Query(value="select aadhar from Aadhar aadhar where aadhar.aadharNo=?1")
	Optional<Aadhar> getAadharByAadharNo2(String aadharNo);
} 