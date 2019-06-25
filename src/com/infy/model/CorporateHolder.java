package com.infy.model;

public class CorporateHolder {
	private Clients clients;
	private Integer time;
	private Integer noOfEmployees;
	private boolean medical;
	
	public Clients getClients() {
		return clients;
	}
	public void setClients(Clients clients) {
		this.clients = clients;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getNoOfEmployees() {
		return noOfEmployees;
	}
	public void setNoOfEmployees(Integer noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}
	public boolean isMedical() {
		return medical;
	}
	public void setMedical(boolean medical) {
		this.medical = medical;
	}
}
