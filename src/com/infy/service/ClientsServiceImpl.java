package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.infy.dao.ClientsDAO;
import com.infy.model.Clients;


@Service("ClientsService")
@Transactional(readOnly = true)
public class ClientsServiceImpl implements ClientsService {

	
	@Autowired
	private ClientsDAO dao;

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Clients register(Clients client) throws Exception {		
		Clients newClient= new Clients();
		
		newClient = dao.register(client);	
		return newClient;
	}
	public Clients login(Clients client) throws Exception{
		Clients newClient= new Clients();
		newClient = dao.login(client);	
		return newClient;
	}
	@Override
	public Clients userIdVerify(String userId) throws Exception {
		Clients value =new Clients();
		value = dao.userIdVerify(userId);	
		return value;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public Clients update(Clients client) throws Exception{
		Clients newClient= new Clients();
		newClient = dao.update(client);	
		return newClient;
	}

	
}


