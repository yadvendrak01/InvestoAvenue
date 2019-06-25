package com.infy.service;

import com.infy.model.Clients;

public interface AlgorithmService {
	public Double individualCar(Clients client,Integer time,Integer price,Float percent,Integer resale) throws Exception;
	
	public Double individualHome(Clients client,Integer time,Integer price,Float percent,Integer resale) throws Exception;
	
	public Double individualEdu(Clients client, Integer age, Integer fee, Float percent)throws Exception;
	
	public Double individualRetire(Clients client) throws Exception;

	public String[] individual(Clients client, Integer time, Integer family,Integer dependents,Integer age) throws Exception;
	
	public String[] corporate(Clients client, Integer time,Integer noOfEmployees,boolean medical)throws Exception;
	
	public String[] medium(Clients client, Integer time,Integer noOfEmployees,boolean medical)throws Exception;

	void pdfGenration(String[] report, Clients client) throws Exception;
}
