package com.deloitte.panservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.panservice.entity.Pan;


@Repository
public interface PanRepository extends JpaRepository<Pan, String> {
	
	@Query(value="select pan from Pan pan where pan.panNo=?1")
	Pan getPanByPanNo(String panNo);
	
	@Query(value="select pan from Pan pan where pan.panNo=?1")
	Optional<Pan> getPanByPanNo2(String panNo);
} 