package com.infy.model;


public class Assets {
	private Integer cash ,
	checkingAccounts,
	saving,
	cashOfLifeInsurance,
	retirement,
	properties,
	noOfVehicles;
	private String medicalInsurance;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	public Integer getCheckingAccounts() {
		return checkingAccounts;
	}
	public void setCheckingAccounts(Integer checkingAccounts) {
		this.checkingAccounts = checkingAccounts;
	}
	public Integer getSaving() {
		return saving;
	}
	public void setSaving(Integer saving) {
		this.saving = saving;
	}
	public Integer getCashOfLifeInsurance() {
		return cashOfLifeInsurance;
	}
	public void setCashOfLifeInsurance(Integer cashOfLifeInsurance) {
		this.cashOfLifeInsurance = cashOfLifeInsurance;
	}
	public Integer getRetirement() {
		return retirement;
	}
	public void setRetirement(Integer retirement) {
		this.retirement = retirement;
	}
	public Integer getProperties() {
		return properties;
	}
	public void setProperties(Integer properties) {
		this.properties = properties;
	}
	public Integer getNoOfVehicles() {
		return noOfVehicles;
	}
	public void setNoOfVehicles(Integer noOfVehicles) {
		this.noOfVehicles = noOfVehicles;
	}
	
	public String getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
}
