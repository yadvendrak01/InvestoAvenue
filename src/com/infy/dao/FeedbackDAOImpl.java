package com.infy.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.entity.ClientsEntity;
import com.infy.entity.FeedbackEntity;
import com.infy.model.Feedback;

@Repository("feedbackdao")
public class FeedbackDAOImpl implements FeedbackDAO{

	@Autowired
	SessionFactory sessionFactory;

	public Feedback review(Feedback feedback) throws Exception {
		
		Session session = null;
		session = sessionFactory.getCurrentSession();
		FeedbackEntity entity=new FeedbackEntity();
		ClientsEntity clientsEntity = session.get(ClientsEntity.class,feedback.getUserId());
		entity.setClient(clientsEntity);
		entity.setFeedbackMessage(feedback.getFeedbackMessage());
		entity.setStar(feedback.getStar());
		session.persist(entity);
		feedback.setMessage("Feedback Recorded!!!");
		return feedback;
	
	}

	@Override
	public List<Feedback> topfeedback() throws Exception {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder2=session.getCriteriaBuilder();
        CriteriaQuery<FeedbackEntity> criteriaQuery2= builder2.createQuery(FeedbackEntity.class);
        Root<FeedbackEntity> root2 = criteriaQuery2.from(FeedbackEntity.class);
        criteriaQuery2.select(root2);
        criteriaQuery2.orderBy(builder2.desc(root2.get("star")));
        List<FeedbackEntity> emplist=session.createQuery(criteriaQuery2).getResultList();          
     
        List<Feedback> lf=new ArrayList<>();
        for(int i=0;i<3;i++)
        {
        	
        	Feedback f=new Feedback(emplist.get(i));
        	lf.add(f);
        }
        return lf;
	}

}
