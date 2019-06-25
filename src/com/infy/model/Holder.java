package com.infy.model;

public class Holder {
private Assets assets;
private Liabilities liabilities;
private Expenses expenses;
private String userId;
public Assets getAssets() {
	return assets;
}
public void setAssets(Assets assets) {
	this.assets = assets;
}
public Liabilities getLiabilities() {
	return liabilities;
}
public void setLiabilities(Liabilities liabilities) {
	this.liabilities = liabilities;
}
public Expenses getExpenses() {
	return expenses;
}
public void setExpenses(Expenses expenses) {
	this.expenses = expenses;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
}
