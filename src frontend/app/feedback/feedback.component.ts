import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { InvestoAvenueService } from '../investo-avenue.service';
import { FormGroup } from '@angular/forms/src/model';
import { Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Clients } from '../clients';
import { Router } from '@angular/router';

@Component({
  selector: 'feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  feedbackForm:FormGroup;
  successMessage:string;
  client:Clients;
  errorMessage:string;
  constructor(private fb:FormBuilder,private investAvenueService:InvestoAvenueService,public authService:AuthService,public route:Router) {
  }

  ngOnInit() {
    this.client=JSON.parse(sessionStorage.getItem('token'));
    this.feedbackForm=this.fb.group({
      userId:["",Validators.required],
      feedbackMessage:["",Validators.required],
      star:["",Validators.required]
    });
    this.feedbackForm.get('userId').setValue(this.client.userId);
  }
  feedbackSubmit(){
    // alert(JSON.stringify(this.feedbackForm.value));
    this.investAvenueService.feedback(this.feedbackForm.value)
    .then((resp)=>{
      this.successMessage=resp.message;
      alert("Your Feedback has been saved .");
      this.route.navigate(['/home']);
    })
    .catch((err)=>{this.errorMessage=err.message})
  }
}
