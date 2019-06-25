package com.infy.model;


public class Liabilities {
	private Integer mortageBalance,creditCardLimit,bankLoan,emis;
	private String userId;
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
