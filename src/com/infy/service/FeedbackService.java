package com.infy.service;

import java.util.List;

import com.infy.model.Feedback;

public interface FeedbackService {
	public Feedback review(Feedback feedback) throws Exception;
	public List<Feedback> topfeedback() throws Exception;
}
