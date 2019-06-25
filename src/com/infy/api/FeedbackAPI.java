package com.infy.api;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.Feedback;
import com.infy.service.FeedbackService;
import com.infy.service.FeedbackServiceImpl;
import com.infy.utility.ContextFactory;

@CrossOrigin
@RestController
@RequestMapping(value="feedback")
public class FeedbackAPI {
	
		private FeedbackService feedbackService;
		private Environment environment;
		
		@RequestMapping(method=RequestMethod.POST, value="review")
		public ResponseEntity<Feedback> review(@RequestBody Feedback f){
			ResponseEntity<Feedback> res=null;
			Feedback fb=new Feedback();
			feedbackService =(FeedbackService) ContextFactory.getContext().getBean(FeedbackServiceImpl.class);
			environment=ContextFactory.getContext().getEnvironment();
			try{
				fb=feedbackService.review(f);
				fb.setMessage(environment.getProperty("Service.FEEDBACK_RECIEVED"));
				res=new ResponseEntity<Feedback>(fb,HttpStatus.OK);
			}catch(Exception e){
				fb.setMessage(environment.getProperty("Service.FEEDBACK_ERROR"));
				res=new ResponseEntity<Feedback>(fb,HttpStatus.BAD_REQUEST);
			}
			return res;

		}
}
