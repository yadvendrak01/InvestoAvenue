package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.ClientsAddDetailsDAO;
import com.infy.model.Assets;
import com.infy.model.Clients;
import com.infy.model.Corporate;
import com.infy.model.Expenses;
import com.infy.model.Liabilities;

@Service("ClientsAddDetailsService")
@Transactional(readOnly = true)
public class ClientsAddDetailsServiceImpl implements ClientsAddDetailsService{
		
		@Autowired
		private ClientsAddDetailsDAO detailsdao;
	
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Clients addInvestmentDetails(Assets assets, Liabilities liabilities, Expenses expenses,String userId) throws Exception {		
			Clients newClient= new Clients();
			
			newClient = detailsdao.addInvestmentDetails(assets, liabilities, expenses, userId);	
			return newClient;
		}

		@Override
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Clients addDetails(Corporate corporate, String userId) throws Exception {
			Clients newClient= new Clients();
			
			newClient = detailsdao.addDetails(corporate, userId);	
			return newClient;
		}

		@Override
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Clients updateDetails(Assets assets, Liabilities liabilities,Expenses expenses, String userId) throws Exception {
			Clients newClient= new Clients();
			newClient=detailsdao.updateDetails(assets, liabilities, expenses, userId);
			return newClient;
		}

		@Override
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Clients updateCorporateDetails(Corporate corporate, String userId)throws Exception {
			Clients newClient= new Clients();
			newClient=detailsdao.updateCorporateDetails(corporate, userId);
			return newClient;
		}
		
}
