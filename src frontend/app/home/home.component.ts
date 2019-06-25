import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { Clients } from '../clients';
import { Output } from '@angular/core';
import { InvestoAvenueService } from '../investo-avenue.service';
import { Feedback } from '../feedback';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  name:string;
  userId:string;
  client:Clients;
  homeMessage:string;
  userFeedback:Feedback[];
  errorMessage:string;

  base={}
  feedName=Object.create(this.base);
  constructor(private router:Router,public authService:AuthService,public investoAvenue:InvestoAvenueService) { }

  ngOnInit() {
    this.client=null;
    this.name=null;
    this.userId=null;
    this.homeMessage=null;
    if(sessionStorage.getItem('isLoggedIn')=="true"){
      this.client=JSON.parse(sessionStorage.getItem('token'));
      this.name=this.client.name;
      this.userId=this.client.userId;
      if(sessionStorage.getItem('counter')=="0"){
        
        sessionStorage.setItem('counter','1');
        location.reload();
      }
    }else{
      this.homeMessage="Hello There Please Login";
    }
  this.getFeedback();
  
  // for(let i of this.userFeedback){
  //   this.feedName.push(this.getUserById(i.userId));
  // }
  }

getFeedback(){
  this.investoAvenue.getFeedback()
  .then(resp=>{
    this.userFeedback=resp;
    for(let user of  this.userFeedback){
      this.getUserById(user.userId)

    }
  })
  .catch(err=>this.errorMessage=err.message)
}


getUserById(data):any{
    this.investoAvenue.userIdVerify(data)
  .then(resp=>{ 
    this.feedName[data]=resp.name;
  })
  .catch((err)=>{
    return "error";
  })
}


createRange(number){
  var items: number[] = [];
  for(var i = 1; i <= number; i++){
     items.push(i);
  }
  return items;
}
}
