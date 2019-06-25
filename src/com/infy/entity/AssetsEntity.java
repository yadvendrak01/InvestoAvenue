package com.infy.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Assets")
@GenericGenerator(name = "pkgen", strategy = "increment")
public class AssetsEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer AssetsId;
	public Integer getAssetsId() {
		return AssetsId;
	}
	public void setAssetsId(Integer assetsId) {
		AssetsId = assetsId;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	private ClientsEntity client;
	private Integer cash,
	checkingAccounts,
	saving,
	cashOfLifeInsurance,
	retirement,
	properties,
	noOfVehicles;
	private String medicalInsurance;
	
	
	
	
	
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
	public ClientsEntity getClient() {
		return client;
	}
	public void setClient(ClientsEntity client) {
		this.client = client;
	}
	
}
