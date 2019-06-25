package com.infy.model;

import com.infy.entity.FeedbackEntity;

public class Feedback {
private Integer star;
private String feedbackMessage;
private String userId;
private String message;
public Feedback(FeedbackEntity feedbackEntity) {
	this.userId=feedbackEntity.getClient().getUserId();
	this.star=feedbackEntity.getStar();
	this.feedbackMessage=feedbackEntity.getFeedbackMessage();
}
public Feedback() {
	this.userId=null;
	this.star=null;
	this.feedbackMessage=null;
	this.message=null;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Integer getStar() {
	return star;
}
public void setStar(Integer star) {
	this.star = star;
}
public String getFeedbackMessage() {
	return feedbackMessage;
}
public void setFeedbackMessage(String feedbackMessage) {
	this.feedbackMessage = feedbackMessage;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}


}
