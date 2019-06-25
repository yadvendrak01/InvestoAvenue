package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Corporate")
@GenericGenerator(name = "pkgen", strategy = "increment")
public class CorporateEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer corporateId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private ClientsEntity client;
	private Integer cash,
	account,
	debts ,
	property ,
	netProfit;
	public Integer getCorporateId() {
		return corporateId;
	}
	public void setCorporateId(Integer corporateId) {
		this.corporateId = corporateId;
	}
	public ClientsEntity getClient() {
		return client;
	}
	public void setClient(ClientsEntity client) {
		this.client = client;
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
