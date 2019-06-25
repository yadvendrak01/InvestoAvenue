package com.infy.model;

import java.time.LocalDate;


public class Clients {
	

	private String name;
	private String typeOfClient;
	private String password;
	private String userId;
	private Long contactNo;
	private String email;
	private LocalDate cdate;
	private String message;
	private String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTypeOfClient() {
		return typeOfClient;
	}
	public void setTypeOfClient(String typeOfClient) {
		this.typeOfClient = typeOfClient;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getCdate() {
		return cdate;
	}
	public void setCdate(LocalDate cdate) {
		this.cdate = cdate;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
