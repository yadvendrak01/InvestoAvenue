package com.infy.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.AssetsEntity;
import com.infy.entity.CorporateEntity;
import com.infy.entity.ExpensesEntity;
import com.infy.entity.LiabilitiesEntity;
import com.infy.model.Clients;

@Repository("algodao")
public class AlgorithmDAOImpl implements AlgorithmDAO{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Integer getNetAssets(Clients client) throws Exception {
		Session session = null;
		session = sessionFactory.getCurrentSession();
     
		CriteriaBuilder builder2=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery2= builder2.createQuery(Object[].class);
        Root<AssetsEntity> root2 = criteriaQuery2.from(AssetsEntity.class);
       System.out.println(client);
        criteriaQuery2.where(builder2.equal(root2.get("client").get("userId"),client.getUserId()));
        criteriaQuery2.multiselect(root2.get("cash"),root2.get("saving"),root2.get("checkingAccounts"));
        Object[] assetsSaving=session.createQuery(criteriaQuery2).uniqueResult();          
        
        CriteriaBuilder builder3=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery3= builder3.createQuery(Object[].class);
        Root<LiabilitiesEntity> root3 = criteriaQuery3.from(LiabilitiesEntity.class);
        criteriaQuery3.where(builder3.equal(root3.get("client").get("userId"),client.getUserId()));
        criteriaQuery3.multiselect(root3.get("mortageBalance"),root3.get("creditCardLimit"),root3.get("bankLoan"),root3.get("emis"));
        Object[] liable=session.createQuery(criteriaQuery3).uniqueResult();          
     
        
        Integer i=((Integer)assetsSaving[0]+(Integer)assetsSaving[1]+(Integer)assetsSaving[2]);
		i-=((Integer)liable[0]+(Integer)liable[1]+(Integer)liable[2]+(Integer)liable[3]);
		System.out.println(i);
        return i;
	}
	public Integer getAnnualSavings(Clients clients) throws Exception{
		
		Session session = null;
		session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery= builder.createQuery(Object[].class);
        Root<ExpensesEntity> root = criteriaQuery.from(ExpensesEntity.class);
        System.out.println("here");
        criteriaQuery.where(builder.equal(root.get("client").get("userId"),clients.getUserId()));
        criteriaQuery.multiselect(root.get("income"),root.get("expenditure"));
        Object[] savings=session.createQuery(criteriaQuery).uniqueResult();          
        
        Integer i =((Integer)savings[0]-(Integer)savings[1]);
        System.out.println("income"+i);
		return i;
	}
@Override	
public Integer[] getRetireSavings(Clients clients) throws Exception{
		
		Session session = null;
		session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder2=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery2= builder2.createQuery(Object[].class);
        Root<AssetsEntity> root2 = criteriaQuery2.from(AssetsEntity.class);
        System.out.println(clients);
        criteriaQuery2.where(builder2.equal(root2.get("client").get("userId"),clients.getUserId()));
        criteriaQuery2.multiselect(root2.get("retirement"),root2.get("cash"));
        Object[] assetsSaving=session.createQuery(criteriaQuery2).uniqueResult();   
        
        
		CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery= builder.createQuery(Object[].class);
        Root<ExpensesEntity> root = criteriaQuery.from(ExpensesEntity.class);

        criteriaQuery.where(builder.equal(root.get("client").get("userId"),clients.getUserId()));
        criteriaQuery.multiselect(root.get("income"),root.get("expenditure"));
        Object[] savings=session.createQuery(criteriaQuery).uniqueResult();          
        
        Integer[] i=new Integer[3];
        i[0]=(Integer)savings[0];
        i[1]=(Integer)assetsSaving[0];
        System.out.println("income"+i);
		return i;
	}
@Override
public Integer[] getNetProfit(String userId) throws Exception{
	
	Session session = null;
	session = sessionFactory.getCurrentSession();
	CriteriaBuilder builder=session.getCriteriaBuilder();
    CriteriaQuery<Object[]> criteriaQuery= builder.createQuery(Object[].class);
    Root<CorporateEntity> root = criteriaQuery.from(CorporateEntity.class);
    System.out.println("here");
    criteriaQuery.where(builder.equal(root.get("client").get("userId"),userId));
    criteriaQuery.multiselect(root.get("netProfit"),root.get("property"),root.get("debts"));
    Object[] obj=session.createQuery(criteriaQuery).uniqueResult();          
    System.out.println("avail");
    Integer[] i=new Integer[3];
    i[0]=(Integer) obj[0];
    i[1]=(Integer) obj[1];
    i[2]=(Integer) obj[2];
	return i;
}
}
