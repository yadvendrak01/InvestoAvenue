package com.infy.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.AssetsEntity;
import com.infy.entity.ClientsEntity;
import com.infy.entity.CorporateEntity;
import com.infy.entity.ExpensesEntity;
import com.infy.entity.LiabilitiesEntity;
import com.infy.model.Assets;
import com.infy.model.Clients;
import com.infy.model.Corporate;
import com.infy.model.Expenses;
import com.infy.model.Liabilities;

@Repository("detailsdao")
public class ClientsAddDetailsDAOImpl implements ClientsAddDetailsDAO{
	@Autowired
	SessionFactory sessionFactory;

	public Clients addInvestmentDetails(Assets assets, Liabilities liabilities, Expenses expenses,String userId) throws Exception 
	{
		Session session = null;
		session = sessionFactory.getCurrentSession();
		AssetsEntity assetsEntity = new AssetsEntity();
		LiabilitiesEntity liabilitiesEntity = new LiabilitiesEntity();
		ExpensesEntity expensesEntity = new ExpensesEntity();
		
		ClientsEntity clientsEntity = session.get(ClientsEntity.class,userId);
		clientsEntity.setDetail("1");
		
		assetsEntity.setCash(assets.getCash());;
		assetsEntity.setCashOfLifeInsurance(assets.getCashOfLifeInsurance());
		assetsEntity.setClient(clientsEntity);
		assetsEntity.setCheckingAccounts(assets.getCheckingAccounts());
		assetsEntity.setMedicalInsurance(assets.getMedicalInsurance());
		assetsEntity.setNoOfVehicles(assets.getNoOfVehicles());
		assetsEntity.setProperties(assets.getProperties());
		assetsEntity.setRetirement(assets.getRetirement());
		assetsEntity.setSaving(assets.getSaving());
		
		liabilitiesEntity.setBankLoan(liabilities.getBankLoan());
		liabilitiesEntity.setCreditCardLimit(liabilities.getCreditCardLimit());
		liabilitiesEntity.setEmis(liabilities.getEmis());
		liabilitiesEntity.setMortageBalance(liabilities.getMortageBalance());
		liabilitiesEntity.setClient(clientsEntity);
		
		expensesEntity.setExpenditure(expenses.getExpenditure());
		expensesEntity.setIncome(expenses.getIncome());
		expensesEntity.setClient(clientsEntity);
		
		session.persist(assetsEntity);
		session.persist(liabilitiesEntity);
		session.persist(expensesEntity);
		
		Clients client = new Clients();
		client.setCdate(clientsEntity.getCdate());
		client.setContactNo(clientsEntity.getContactNo());
		client.setEmail(clientsEntity.getEmail());
		client.setName(clientsEntity.getName());
		client.setPassword(clientsEntity.getPassword());
		client.setTypeOfClient(clientsEntity.getTypeOfClient());
		client.setDetail(clientsEntity.getDetail());
		client.setUserId(clientsEntity.getUserId());
		return client;
	}

	@Override
	public Clients addDetails(Corporate corporate,String userId) throws Exception {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		
		CorporateEntity corporateEntity=new CorporateEntity();
		ClientsEntity clientsEntity = session.get(ClientsEntity.class,userId);
		clientsEntity.setDetail("1");
		
		corporateEntity.setAccount(corporate.getAccount());
		corporateEntity.setCash(corporate.getCash());
		corporateEntity.setClient(clientsEntity);
		corporateEntity.setDebts(corporate.getDebts());
		corporateEntity.setNetProfit(corporate.getNetProfit());
		corporateEntity.setProperty(corporate.getProperty());
		
		session.persist(corporateEntity);
		
		Clients client = new Clients();
		client.setCdate(clientsEntity.getCdate());
		client.setContactNo(clientsEntity.getContactNo());
		client.setEmail(clientsEntity.getEmail());
		client.setName(clientsEntity.getName());
		client.setPassword(clientsEntity.getPassword());
		client.setTypeOfClient(clientsEntity.getTypeOfClient());
		client.setDetail(clientsEntity.getDetail());
		client.setUserId(clientsEntity.getUserId());
		return client;
	}

	@Override
	public Clients updateDetails(Assets assets, Liabilities liabilities,Expenses expenses, String userId) throws Exception {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		AssetsEntity assetsEntity = new AssetsEntity();
		LiabilitiesEntity liabilitiesEntity = new LiabilitiesEntity();
		ExpensesEntity expensesEntity = new ExpensesEntity();
		
		ClientsEntity clientsEntity = session.get(ClientsEntity.class,userId);
		clientsEntity.setDetail("1");
		
		CriteriaBuilder builder3=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery3= builder3.createQuery(Object[].class);
        Root<AssetsEntity> root3 = criteriaQuery3.from(AssetsEntity.class);
        criteriaQuery3.where(builder3.equal(root3.get("client").get("userId"),userId));
        criteriaQuery3.select(root3.get("AssetsId"));
        
        Object ass=session.createQuery(criteriaQuery3).uniqueResult();

        assetsEntity = session.get(AssetsEntity.class, (Integer)ass);
		assetsEntity.setCash(assets.getCash());
		assetsEntity.setCashOfLifeInsurance(assets.getCashOfLifeInsurance());
		assetsEntity.setClient(clientsEntity);
		assetsEntity.setCheckingAccounts(assets.getCheckingAccounts());
		assetsEntity.setMedicalInsurance(assets.getMedicalInsurance());
		assetsEntity.setNoOfVehicles(assets.getNoOfVehicles());
		assetsEntity.setProperties(assets.getProperties());
		assetsEntity.setRetirement(assets.getRetirement());
		assetsEntity.setSaving(assets.getSaving());
		
		CriteriaBuilder builder2=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery2= builder2.createQuery(Object[].class);
        Root<LiabilitiesEntity> root2 = criteriaQuery2.from(LiabilitiesEntity.class);
        criteriaQuery2.where(builder2.equal(root2.get("client").get("userId"),userId));
        criteriaQuery2.select(root2.get("LiabilityId"));
        
        Object liab=session.createQuery(criteriaQuery2).uniqueResult();

        liabilitiesEntity = session.get(LiabilitiesEntity.class, (Integer)liab);
		liabilitiesEntity.setBankLoan(liabilities.getBankLoan());
		liabilitiesEntity.setCreditCardLimit(liabilities.getCreditCardLimit());
		liabilitiesEntity.setEmis(liabilities.getEmis());
		liabilitiesEntity.setMortageBalance(liabilities.getMortageBalance());
		liabilitiesEntity.setClient(clientsEntity);
		
		CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery= builder.createQuery(Object[].class);
        Root<ExpensesEntity> root = criteriaQuery.from(ExpensesEntity.class);
        criteriaQuery.where(builder.equal(root.get("client").get("userId"),userId));
        criteriaQuery.select(root.get("ExpensesId"));
        
        Object exp=session.createQuery(criteriaQuery).uniqueResult();
		
        expensesEntity = session.get(ExpensesEntity.class, (Integer)exp);
		expensesEntity.setExpenditure(expenses.getExpenditure());
		expensesEntity.setIncome(expenses.getIncome());
		expensesEntity.setClient(clientsEntity);
		
		session.persist(assetsEntity);
		session.persist(liabilitiesEntity);
		session.persist(expensesEntity);
		
		Clients client = new Clients();
		client.setCdate(clientsEntity.getCdate());
		client.setContactNo(clientsEntity.getContactNo());
		client.setEmail(clientsEntity.getEmail());
		client.setName(clientsEntity.getName());
		client.setPassword(clientsEntity.getPassword());
		client.setTypeOfClient(clientsEntity.getTypeOfClient());
		client.setDetail(clientsEntity.getDetail());
		client.setUserId(clientsEntity.getUserId());
		return client;
	}

	@Override
	public Clients updateCorporateDetails(Corporate corporate, String userId)throws Exception {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		
		CorporateEntity corporateEntity=new CorporateEntity();
		ClientsEntity clientsEntity = session.get(ClientsEntity.class,userId);
		clientsEntity.setDetail("1");
		
		CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery= builder.createQuery(Object[].class);
        Root<CorporateEntity> root = criteriaQuery.from(CorporateEntity.class);
        criteriaQuery.where(builder.equal(root.get("client").get("userId"),userId));
        criteriaQuery.select(root.get("corporateId"));
        

        Object exp=session.createQuery(criteriaQuery).uniqueResult();
		
        corporateEntity = session.get(CorporateEntity.class, (Integer)exp);
		corporateEntity.setAccount(corporate.getAccount());
		corporateEntity.setCash(corporate.getCash());
		corporateEntity.setClient(clientsEntity);
		corporateEntity.setDebts(corporate.getDebts());
		corporateEntity.setNetProfit(corporate.getNetProfit());
		corporateEntity.setProperty(corporate.getProperty());
		
		session.persist(corporateEntity);
		
		Clients client = new Clients();
		client.setCdate(clientsEntity.getCdate());
		client.setContactNo(clientsEntity.getContactNo());
		client.setEmail(clientsEntity.getEmail());
		client.setName(clientsEntity.getName());
		client.setPassword(clientsEntity.getPassword());
		client.setTypeOfClient(clientsEntity.getTypeOfClient());
		client.setDetail(clientsEntity.getDetail());
		client.setUserId(clientsEntity.getUserId());
		return client;
		
	}

}
