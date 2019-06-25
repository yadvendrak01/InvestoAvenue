package com.infy.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.AssetsEntity;
import com.infy.entity.ExpensesEntity;
import com.infy.model.Assets;
@Repository("algo2DAO")

public class Algorithm2DAOImpl implements Algorithm2DAO{

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Assets individualAssets(String userId) throws Exception {
		System.out.println("aaya");
		Session session = null;
		session = sessionFactory.getCurrentSession();
		
		
		CriteriaBuilder builder2=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery2= builder2.createQuery(Object[].class);
        
        Root<AssetsEntity> root2 = criteriaQuery2.from(AssetsEntity.class);
        criteriaQuery2.where(builder2.equal(root2.get("client").get("userId"),userId));
        
        criteriaQuery2.multiselect(root2.get("medicalInsurance"),root2.get("properties"),root2.get("noOfVehicles"),root2.get("retirement"),root2.get("cashOfLifeInsurance"));
        Object[] assets=session.createQuery(criteriaQuery2).uniqueResult();  
        
        Assets a=new Assets();
        a.setMedicalInsurance((String)assets[0]);
        a.setProperties((Integer)assets[1]);
        a.setNoOfVehicles((Integer)assets[2]);
        a.setRetirement((Integer)assets[3]);
        a.setCashOfLifeInsurance((Integer)assets[4]);
        return a;
        
		
	}
	@Override
	public Integer individualIncome(String userId) throws Exception {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery= builder.createQuery(Object[].class);
        Root<ExpensesEntity> root = criteriaQuery.from(ExpensesEntity.class);
        criteriaQuery.where(builder.equal(root.get("client").get("userId"),userId));
        criteriaQuery.select(root.get("income"));
        
        Object savings=session.createQuery(criteriaQuery).uniqueResult();
        Integer i=(Integer)savings;
        return i;
	}
}
