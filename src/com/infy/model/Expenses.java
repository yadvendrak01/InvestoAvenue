package com.infy.model;


public class Expenses {
	private Integer income;
	private Integer expenditure;
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(Integer expenditure) {
		this.expenditure = expenditure;
	}
		
}
