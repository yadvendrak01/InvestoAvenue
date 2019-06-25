package com.infy.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Liabilities")
@GenericGenerator(name = "pkgen", strategy = "increment")
public class LiabilitiesEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer LiabilityId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private ClientsEntity client;
	public Integer getLiabilityId() {
		return LiabilityId;
	}
	public void setLiabilityId(Integer liabilityId) {
		LiabilityId = liabilityId;
	}
	private Integer mortageBalance,creditCardLimit,bankLoan,emis;
	
	
	public Integer getMortageBalance() {
		return mortageBalance;
	}
	public void setMortageBalance(Integer mortageBalance) {
		this.mortageBalance = mortageBalance;
	}
	public Integer getCreditCardLimit() {
		return creditCardLimit;
	}
	public void setCreditCardLimit(Integer creditCardLimit) {
		this.creditCardLimit = creditCardLimit;
	}
	public Integer getBankLoan() {
		return bankLoan;
	}
	public void setBankLoan(Integer bankLoan) {
		this.bankLoan = bankLoan;
	}
	public Integer getEmis() {
		return emis;
	}
	public void setEmis(Integer emis) {
		this.emis = emis;
	}
	public ClientsEntity getClient() {
		return client;
	}
	public void setClient(ClientsEntity client) {
		this.client = client;
	}
	
	

}
