package com.infy.service;

import com.infy.model.Clients;




public interface ClientsService {
	
	public Clients register(Clients client) throws Exception;
	public Clients login(Clients client) throws Exception;
	public Clients userIdVerify(String userId)throws Exception;
	Clients update(Clients client) throws Exception;
}
