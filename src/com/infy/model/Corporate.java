package com.infy.model;


public class Corporate {
	private String userId;
	private Integer cash,
	account,
	debts ,
	property ,
	netProfit;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	public Integer getAccount() {
		return account;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}
	public Integer getDebts() {
		return debts;
	}
	public void setDebts(Integer debts) {
		this.debts = debts;
	}
	public Integer getProperty() {
		return property;
	}
	public void setProperty(Integer property) {
		this.property = property;
	}
	public Integer getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(Integer netProfit) {
		this.netProfit = netProfit;
	}
	
}
