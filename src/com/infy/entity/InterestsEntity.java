package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Interests")
@GenericGenerator(name = "pkgen", strategy = "increment")
public class InterestsEntity {
	@Id
	@GeneratedValue(generator = "pkgen")
	private Integer interestId;
	private String interest;
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	private Integer timeFrame;
	private Integer amount;
	@ManyToOne
	@JoinColumn(name = "userId")
	private ClientsEntity client;

	
	public Integer getInterestId() {
		return interestId;
	}
	public void setInterestId(Integer interestId) {
		this.interestId = interestId;
	}
	public Integer getTimeFrame() {
		return timeFrame;
	}
	public void setTimeFrame(Integer timeFrame) {
		this.timeFrame = timeFrame;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public ClientsEntity getClient() {
		return client;
	}
	public void setClient(ClientsEntity client) {
		this.client = client;
	}
	
	

}
