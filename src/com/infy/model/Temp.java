package com.infy.model;

public class Temp {
	private Assets assets;
	private Liabilities liabilities;
	private Expenses expenses;
	private Clients clients;
	public Clients getClients() {
		return clients;
	}
	public void setClients(Clients clients) {
		this.clients = clients;
	}
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
}
