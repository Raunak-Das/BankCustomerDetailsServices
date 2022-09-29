package com.deloitte.panservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pan")
public class Pan {
	
	@Id
	@Column(length=12)
	private String panNo;
	
	@Column(length=20)
	private String firstName;
	
	@Column(length=20)
	private String lastName;
	
	@Column(columnDefinition="DATE")
	private Date dob;
	
	public Pan(String panNo, String firstName, String lastName, Date dob) {
		super();
		this.panNo = panNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}
	
	
	public Pan() {
		super();
	}

	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

}
