package com.infy.dao;

import com.infy.model.Clients;




public interface ClientsDAO {
	
	public Clients register(Clients client) throws Exception;
	public Clients login(Clients client) throws Exception;
	public Clients userIdVerify(String userId)throws Exception;
	Clients update(Clients client) throws Exception;


}
