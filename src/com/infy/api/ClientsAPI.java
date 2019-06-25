package com.infy.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.Clients;
import com.infy.model.CorporateHolder;
import com.infy.model.EduHolder;
import com.infy.model.Feedback;
import com.infy.model.Holder;
import com.infy.model.Holder2;
import com.infy.model.HolderCar;
import com.infy.model.Temporary;
import com.infy.service.AlgorithmService;
import com.infy.service.AlgorithmServiceImpl;
import com.infy.service.ClientsAddDetailsService;
import com.infy.service.ClientsAddDetailsServiceImpl;
import com.infy.service.ClientsService;
import com.infy.service.ClientsServiceImpl;
import com.infy.service.FeedbackService;
import com.infy.service.FeedbackServiceImpl;
import com.infy.utility.ContextFactory;



@CrossOrigin
@RestController
@RequestMapping(value="client")
public class ClientsAPI {
	
	private ClientsService service;
	private Environment environment;
	private FeedbackService feedbackService;
	private ClientsAddDetailsService cService;
	private AlgorithmService algoService;
	
	@RequestMapping(method=RequestMethod.POST, value="register")
	public ResponseEntity<Clients> register(@RequestBody Clients c){
		ResponseEntity<Clients> res=null;
		Clients ca=new Clients();
		service =(ClientsService) ContextFactory.getContext().getBean(ClientsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			ca=service.register(c);
			ca.setMessage(environment.getProperty("Service.REGISTRATION_SUCCESS"));
			res=new ResponseEntity<Clients>(ca,HttpStatus.OK);
		}catch(Exception e){
			ca.setMessage(environment.getProperty("Service.REGISTRATION_FAILED"));
			res=new ResponseEntity<Clients>(ca,HttpStatus.BAD_REQUEST);
		}
		
		return res;

	}
	
	@RequestMapping(method=RequestMethod.POST, value="login")
	public ResponseEntity<Clients> login(@RequestBody Clients c){
		ResponseEntity<Clients> res=null;
		Clients ca=new Clients();
		service =(ClientsService) ContextFactory.getContext().getBean(ClientsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		
		try{
			ca=service.login(c);
			if(ca.getPassword().equals(c.getPassword()))
				ca.setMessage(environment.getProperty("Service.LOGING_SUCCESS"));
			else
				ca.setMessage(environment.getProperty("Service.LOGIN_FAILED"));	
			res=new ResponseEntity<Clients>(ca,HttpStatus.OK);
			
		}catch(Exception e){
			ca.setMessage(environment.getProperty("Service.LOGIN_FAILED"));
			res=new ResponseEntity<Clients>(ca,HttpStatus.BAD_REQUEST);
		}
		
		return res;

	}
	
	@RequestMapping(method=RequestMethod.GET, value="useridverify/{userId}")
	public ResponseEntity<Clients> userIdVerify(@PathVariable String userId){
		ResponseEntity<Clients> res=null;
		service =(ClientsService) ContextFactory.getContext().getBean(ClientsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		Clients ca=new Clients();
		try{
			ca=service.userIdVerify(userId);
			if(ca.getMessage()==null)
				ca.setMessage(environment.getProperty("Service.UNIQUE_USERID"));
			else
				ca.setMessage(environment.getProperty("Service.USERID_ALREADY_EXIST"));	
			res=new ResponseEntity<Clients>(ca,HttpStatus.OK);
			
		}catch(Exception e){
			ca.setMessage(environment.getProperty("DAO.TECHNICAL_ERROR"));
			res=new ResponseEntity<Clients>(ca,HttpStatus.BAD_REQUEST);
		}
		
		return res;

	}
	

	
	
	
	
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
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="feedbackDisplay")
	public ResponseEntity<List<Feedback>> topFeedback(){
		ResponseEntity<List<Feedback>> res=null;
		Feedback fb=new Feedback();
		List<Feedback> lf=new ArrayList<>();
		feedbackService =(FeedbackService) ContextFactory.getContext().getBean(FeedbackServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			lf=feedbackService.topfeedback();
			res=new ResponseEntity<List<Feedback>>(lf,HttpStatus.OK);
		}catch(Exception e){
			fb.setMessage(environment.getProperty("Service.FEEDBACK_ERROR"));
			res=new ResponseEntity<List<Feedback>>(lf,HttpStatus.BAD_REQUEST);
		}
		return res;

	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="addIndividualDetails")
	public ResponseEntity<Clients> addInvestmentDetails(@RequestBody Holder holder){
		ResponseEntity<Clients> res=null;
		Clients client=new Clients();
		cService = (ClientsAddDetailsService) ContextFactory.getContext().getBean(ClientsAddDetailsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			client=cService.addInvestmentDetails(holder.getAssets(),holder.getLiabilities(),holder.getExpenses(),holder.getUserId());
			client.setMessage("Service_RECIEVED");
			res=new ResponseEntity<Clients>(client,HttpStatus.OK);
		}catch(Exception e){
			client.setMessage("Service_ERROR");
			res=new ResponseEntity<Clients>(client,HttpStatus.BAD_REQUEST);
		}
		return res;

	}
	
	@RequestMapping(method=RequestMethod.POST, value="addCorporateDetails")
	public ResponseEntity<Clients> addDetails(@RequestBody Holder2 holder){
		ResponseEntity<Clients> res=null;
		Clients client=new Clients();
		cService = (ClientsAddDetailsService) ContextFactory.getContext().getBean(ClientsAddDetailsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			client=cService.addDetails(holder.getCorporate(),holder.getUserId());
			client.setMessage("Service_RECIEVED");
			res=new ResponseEntity<Clients>(client,HttpStatus.OK);
		}catch(Exception e){
			client.setMessage("Service_ERROR");
			res=new ResponseEntity<Clients>(client,HttpStatus.BAD_REQUEST);
		}
		return res;

	}
	
	@RequestMapping(method=RequestMethod.POST, value="update")
	public ResponseEntity<Clients> update(@RequestBody Clients c){
		ResponseEntity<Clients> res=null;
		Clients ca=new Clients();
		service =(ClientsService) ContextFactory.getContext().getBean(ClientsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		
		try{
			ca=service.update(c);
			ca.setMessage(environment.getProperty("Service.UPDATE_SUCCESS"));
			res=new ResponseEntity<Clients>(ca,HttpStatus.OK);
			
		}catch(Exception e){
			ca.setMessage(environment.getProperty("Service.UPDATE_FAILED"));
			res=new ResponseEntity<Clients>(ca,HttpStatus.BAD_REQUEST);
		}
		
		return res;

	}
	
	@RequestMapping(method=RequestMethod.POST, value="individualCar")
	public ResponseEntity<Double> individualCar(@RequestBody HolderCar holder){
		ResponseEntity<Double> res=null;
		Double arr=0d;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.individualCar(holder.getClients(), holder.getTime(), holder.getPrice(), holder.getPercent(), holder.getResale());
			res=new ResponseEntity<Double>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<Double>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	@RequestMapping(method=RequestMethod.POST, value="individualHome")
	public ResponseEntity<Double> individualHome(@RequestBody HolderCar holder){
		ResponseEntity<Double> res=null;
		Double arr=0d;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.individualHome(holder.getClients(), holder.getTime(), holder.getPrice(), holder.getPercent(), holder.getResale());
			res=new ResponseEntity<Double>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<Double>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	@RequestMapping(method=RequestMethod.POST, value="individualEdu")
	public ResponseEntity<Double> individualEdu(@RequestBody EduHolder holder){
		ResponseEntity<Double> res=null;
		Double arr=0d;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.individualEdu(holder.getClients(), holder.getAge(), holder.getFee(), holder.getPercent());
			res=new ResponseEntity<Double>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<Double>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	@RequestMapping(method=RequestMethod.POST, value="individualRetire")
	public ResponseEntity<Double> individualEdu(@RequestBody Clients clients){
		ResponseEntity<Double> res=null;
		Double arr=0d;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.individualRetire(clients);
			res=new ResponseEntity<Double>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<Double>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	@RequestMapping(method=RequestMethod.POST, value="individual")
	public ResponseEntity<String[]> individual(@RequestBody Temporary temp){
		ResponseEntity<String[]> res=null;
		String[] arr=null;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.individual(temp.getClients(),temp.getTime(),temp.getFamily(),temp.getDependents(),temp.getAge());
			algoService.pdfGenration(arr,temp.getClients());
			res=new ResponseEntity<String[]>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<String[]>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="updateDetails")
	public ResponseEntity<Clients> updateInvestmentDetails(@RequestBody Holder holder){
		ResponseEntity<Clients> res=null;
		Clients client=new Clients();
		cService = (ClientsAddDetailsService) ContextFactory.getContext().getBean(ClientsAddDetailsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			client=cService.updateDetails(holder.getAssets(),holder.getLiabilities(),holder.getExpenses(),holder.getUserId());
			client.setMessage("Service_RECIEVED");
			res=new ResponseEntity<Clients>(client,HttpStatus.OK);
		}catch(Exception e){
			client.setMessage("Service_ERROR");
			res=new ResponseEntity<Clients>(client,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="updateCorporateDetails")
	public ResponseEntity<Clients> updateCorporateDetails(@RequestBody Holder2 holder){
		ResponseEntity<Clients> res=null;
		Clients client=new Clients();
		cService = (ClientsAddDetailsService) ContextFactory.getContext().getBean(ClientsAddDetailsServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			client=cService.updateCorporateDetails(holder.getCorporate(), holder.getUserId());
			client.setMessage("Service_RECIEVED");
			res=new ResponseEntity<Clients>(client,HttpStatus.OK);
		}catch(Exception e){
			client.setMessage("Service_ERROR");
			res=new ResponseEntity<Clients>(client,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="corporate")
	public ResponseEntity<String[]> corporate(@RequestBody CorporateHolder temp){
		ResponseEntity<String[]> res=null;
		String[] arr=null;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.corporate(temp.getClients(),temp.getTime(),temp.getNoOfEmployees(),temp.isMedical());
			algoService.pdfGenration(arr,temp.getClients());
			res=new ResponseEntity<String[]>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<String[]>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	@RequestMapping(method=RequestMethod.POST, value="medium")
	public ResponseEntity<String[]> medium(@RequestBody CorporateHolder temp){
		ResponseEntity<String[]> res=null;
		String[] arr=null;
		algoService = ContextFactory.getContext().getBean(AlgorithmServiceImpl.class);
		environment=ContextFactory.getContext().getEnvironment();
		try{
			arr=algoService.medium(temp.getClients(),temp.getTime(),temp.getNoOfEmployees(),temp.isMedical());
			algoService.pdfGenration(arr,temp.getClients());
			res=new ResponseEntity<String[]>(arr,HttpStatus.OK);
		}catch(Exception e){
			res=new ResponseEntity<String[]>(arr,HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	

}
