package com.deloitte.bankservice.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column(length=10)
	private int accountNo;
	@Column(length=20)
	private String firstName;
	@Column(length=20)
	private String lastName;
	@Column(length=10)
	private String mobile;
	@Column(length=20)
	private String email;
		
	@Autowired
	@Embedded
	private Address address;

	@Column(length=10)
	private String pancardNo;
	@Column(length=12)
	private String aadharNo;
	@Column
	private String aadharRemarks;
	@Column
	private String panRemarks;
	
	public int getAccountNo() {
		return accountNo;
	}
	public String getAadharRemarks() {
		return aadharRemarks;
	}
	public void setAadharRemarks(String aadharRemarks) {
		this.aadharRemarks = aadharRemarks;
	}
	public String getPanRemarks() {
		return panRemarks;
	}
	public void setPanRemarks(String panRemarks) {
		this.panRemarks = panRemarks;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPancardNo() {
		return pancardNo;
	}
	public void setPancardNo(String pancardNo) {
		this.pancardNo = pancardNo;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	
	public Customer(int accountNo, String firstName, String lastName, String mobile, String email, Address address,
			String pancardNo, String aadharNo, String aadharRemarks, String panRemarks) {
		super();
		this.accountNo = accountNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.pancardNo = pancardNo;
		this.aadharNo = aadharNo;
		this.aadharRemarks = aadharRemarks;
		this.panRemarks = panRemarks;
	}
	
	public Customer() {
		super();
	}
	
}
