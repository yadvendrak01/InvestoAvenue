package com.infy.dao;

import com.infy.model.Assets;
import com.infy.model.Clients;
import com.infy.model.Corporate;
import com.infy.model.Expenses;
import com.infy.model.Liabilities;

public interface ClientsAddDetailsDAO {

	public Clients addInvestmentDetails(Assets assets, Liabilities liabilities, Expenses expenses,String userId ) throws Exception;
	public Clients addDetails(Corporate corporate,String userId) throws Exception;
	public Clients updateCorporateDetails(Corporate corporate,String userId) throws Exception;
	public Clients updateDetails(Assets assets, Liabilities liabilities, Expenses expenses,String userId ) throws Exception;
}
