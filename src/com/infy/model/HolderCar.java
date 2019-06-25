package com.infy.model;

public class HolderCar {
private Clients clients;
private Integer time, price, resale;
private Float percent;

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
public Integer getPrice() {
	return price;
}
public void setPrice(Integer price) {
	this.price = price;
}
public Integer getResale() {
	return resale;
}
public void setResale(Integer resale) {
	this.resale = resale;
}
public Float getPercent() {
	return percent;
}
public void setPercent(Float percent) {
	this.percent = percent;
}
}
