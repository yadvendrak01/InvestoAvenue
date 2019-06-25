package com.infy.model;


public class Interests {
	private Integer timeFrame;
	private Integer amount;
	private String userId;

	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getTimeFrame() {
		return timeFrame;
	}
	public void setTimeFrame(Integer timeFrame) {
		this.timeFrame = timeFrame;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
