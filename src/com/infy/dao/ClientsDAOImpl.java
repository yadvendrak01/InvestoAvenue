package com.infy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.ClientsEntity;
import com.infy.model.Clients;


@Repository("dao")
public class ClientsDAOImpl implements ClientsDAO {

	@Autowired
	SessionFactory sessionFactory;

	public Clients register(Clients client) throws Exception 
	{
		Session session = null;
		session = sessionFactory.getCurrentSession();
		client.setDetail("0");
		ClientsEntity clientsEntity = new ClientsEntity();
		clientsEntity.setUserId(client.getUserId());
		clientsEntity.setName(client.getName());
		clientsEntity.setPassword(client.getPassword());
		clientsEntity.setTypeOfClient(client.getTypeOfClient());
		clientsEntity.setCdate(client.getCdate());
		clientsEntity.setEmail(client.getEmail());
		clientsEntity.setContactNo(client.getContactNo());
		clientsEntity.setDetail(client.getDetail());
		session.save(clientsEntity);
		client.setMessage("Registered successfully!!!!");
		return client;
	}
	public Clients login(Clients client) throws Exception 
	{
		Session session = null;
		Clients newClient=new Clients();
		session = sessionFactory.getCurrentSession();
		
		ClientsEntity clientsEntity = session.get(ClientsEntity.class,client.getUserId());
		
		if(client.getPassword().equals(clientsEntity.getPassword()))
			{
			client.setName(clientsEntity.getName());
			client.setCdate(clientsEntity.getCdate());
			client.setContactNo(clientsEntity.getContactNo());
			client.setEmail(clientsEntity.getEmail());
			client.setTypeOfClient(clientsEntity.getTypeOfClient());	
			client.setDetail(clientsEntity.getDetail());
			}
			else
				return newClient;
			
		return client;
	}
	@Override
	public Clients userIdVerify(String userId) throws Exception 
	{
		Session session = null;
		session = sessionFactory.getCurrentSession();
		Clients client=new Clients();
		try{
			ClientsEntity clientsEntity = session.get(ClientsEntity.class,userId);
			client.setName(clientsEntity.getName());
			client.setCdate(clientsEntity.getCdate());
			client.setContactNo(clientsEntity.getContactNo());
			client.setEmail(clientsEntity.getEmail());
			client.setTypeOfClient(clientsEntity.getTypeOfClient());	
			client.setDetail(clientsEntity.getDetail());
			client.setMessage("verified");
		}
		catch(Exception e)
		{
			return client;
		}
		
		return client;
		}
	
	@Override 
	public Clients update(Clients client) throws Exception {
		 Session session = null;
		 session = sessionFactory.getCurrentSession();
		 
		 ClientsEntity ce = new ClientsEntity();
		 ce = session.get(ClientsEntity.class, client.getUserId());
		 ce.setCdate(client.getCdate());
		 ce.setContactNo(client.getContactNo());
		 ce.setEmail(client.getEmail());
		 ce.setName(client.getName());
		 ce.setPassword(client.getPassword());
		 ce.setTypeOfClient(client.getTypeOfClient());
		 ce.setUserId(client.getUserId());
		 ce.setDetail(client.getDetail());
		 session.save(ce);
		 return client;
	 }

}
