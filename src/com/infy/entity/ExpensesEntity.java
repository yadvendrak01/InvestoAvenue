package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Expenses")
@GenericGenerator(name = "pkgen", strategy = "increment")
public class ExpensesEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer ExpensesId;
	public Integer getExpensesId() {
		return ExpensesId;
	}
	public void setExpensesId(Integer expensesId) {
		ExpensesId = expensesId;
	}
	@ManyToOne
	@JoinColumn(name = "userId")
	private ClientsEntity client;
	private Integer income;
	private Integer expenditure;
	
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(Integer expenditure) {
		this.expenditure = expenditure;
	}
	public ClientsEntity getClient() {
		return client;
	}
	public void setClient(ClientsEntity client) {
		this.client = client;
	}
	
}
