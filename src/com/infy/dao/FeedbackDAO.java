package com.infy.dao;

import java.util.List;

import com.infy.model.Feedback;

public interface FeedbackDAO {
	public Feedback review(Feedback feedback) throws Exception;
	public List<Feedback> topfeedback() throws Exception;
}
