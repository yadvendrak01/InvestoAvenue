package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.FeedbackDAO;
import com.infy.model.Feedback;

@Service("feedbackService")
@Transactional(readOnly = true)
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackDAO feedbackdao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Feedback review(Feedback feedback) throws Exception {
		Feedback fb=new Feedback();
		fb=feedbackdao.review(feedback);
		return fb;
	}
	public List<Feedback> topfeedback() throws Exception {
		List<Feedback> list;
		list=feedbackdao.topfeedback();
		return list;
	}
}
