import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InvestoAvenueService } from '../investo-avenue.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { Clients } from '../clients';
import { delay } from 'q';

@Component({
  selector: 'add-detail',
  templateUrl: './add-detail.component.html',
  styleUrls: ['./add-detail.component.css']
})
export class AddDetailComponent implements OnInit {
 client:Clients;
 message:string;
 showForm:boolean;
 showUpdate:boolean;
 adviceMessage:string;
 start:any;
 end:any;
 status:boolean=false;
 showWait:any;
  constructor( private authService:AuthService,private fb:FormBuilder,private investAvenueService:InvestoAvenueService,private router:Router) {
  }

  addDetailsForm:FormGroup;
  mediumCorpForm:FormGroup;
  updateDetailsForm:FormGroup;
  clientGetAdvice:FormGroup;
  corpMediumAdvice:FormGroup;
  ngOnInit() {
    this.adviceMessage=null;
    this.showForm=false;
    this.showUpdate=false;
    this.showWait=false;
    this.client=JSON.parse(sessionStorage.getItem('token'));
    if(this.client.detail==1){
      this.showForm=true;
    }
    this.addDetailsForm=this.fb.group({
      assets:this.fb.group({
      cash:["",[Validators.required,Validators.min(0)]],
      checkingAccounts:["",[Validators.required,Validators.min(0)]],
      saving:["",[Validators.required,Validators.min(0)]],
      cashOfLifeInsurance:["",[Validators.required,Validators.min(0)]],
      retirement:["",[Validators.required,Validators.min(0)]],
      properties:["",[Validators.required,Validators.min(0)]],
      noOfVehicles:["",[Validators.required,Validators.min(0)]],
      medicalInsurance:["",[Validators.required]]
    }),
    liabilities:this.fb.group({
      mortageBalance:["",[Validators.required,Validators.min(0)]],
      creditCardLimit:["",[Validators.required,Validators.min(0)]],
      bankLoan:["",[Validators.required,Validators.min(0)]],
      emis:["",[Validators.required,Validators.min(0)]]
    }),
    expenses:this.fb.group({
      income:["",[Validators.required,Validators.min(0)]],
      expenditure:["",[Validators.required,Validators.min(0)]]
    }),
      
    userId:[this.client.userId]
    
    })

    this.mediumCorpForm=this.fb.group({
      corporate:this.fb.group({
        account:["",[Validators.required,Validators.min(0)]],
        cash :["",[Validators.required,Validators.min(0)]],
        debts:["",[Validators.required,Validators.min(0)]],
        property:["",[Validators.required,Validators.min(0)]],
        netProfit:["",[Validators.required,Validators.min(0)]],
      }),

    userId:[this.client.userId]

    })

    this.clientGetAdvice=this.fb.group({
        clients:[this.client],
        time:["",[Validators.required,Validators.min(1)]],
        family:["",[Validators.required,Validators.min(0)]],
        dependents:["",[Validators.required,Validators.min(0)]],
        age:["",[Validators.required,Validators.min(0)]]
    })

    this.corpMediumAdvice=this.fb.group({
      clients:[this.client],
      time:["",[Validators.required,Validators.min(1)]],
	    noOfEmployees:["",[Validators.required,Validators.min(0)]],
	    medical:["",[Validators.required,Validators.min(0)]]
    })

  }
  addDetails(){
    this.investAvenueService.clientAddDetails(this.addDetailsForm.value)
    .then(resp=>{
      this.client=resp;
      sessionStorage.setItem('token',JSON.stringify(resp))
      this.message=resp.message;
    })
    .catch(err=>{
      this.message=err.message;
    })
  }

  updateDetailsofClient(){
    
    this.investAvenueService.clientUpdateDetails(this.addDetailsForm.value)
    .then(resp=>{
      this.client=resp;
      //  alert(JSON.stringify(resp))
      sessionStorage.setItem('token',JSON.stringify(resp))
      this.message=resp.message;
      this.showUpdate=false;
    })
    .catch(err=>{
      this.message=err.message;
    })
  }

  updateDetailsMediumCorporate(){
    this.investAvenueService.mediumCorporateUpdateDetails(this.mediumCorpForm.value)
    .then(resp=>{
      this.client=resp;
      sessionStorage.setItem('token',JSON.stringify(resp))
      this.message=resp.message;
      this.showUpdate=false;
    })
    .catch(err=>{
      this.message=err.message;
    })
  }
  addDetailsMediumCorporate(){
        this.investAvenueService.mediumCorpDetails(this.mediumCorpForm.value)
        .then(resp=>{
          // alert(JSON.stringify(resp));
          this.client=resp;
          sessionStorage.setItem('token',JSON.stringify(resp))
          this.message=resp.message;
        })
        .catch(err=>{
          this.message=err.message;
        })
  }

  clientGenerateReport(){
    this.showWait=true;
    this.investAvenueService.clientGenerateReport(this.clientGetAdvice.value)
    .then(resp=>{
      // alert(JSON.stringify(resp))
      this.createDelay();
      this.showWait=false;
      this.adviceMessage="payNow";
    })
    .catch(err=>{
      this.message=err.message
    })
  }
  mediumGenerateReport(){
    this.showWait=true;
    this.investAvenueService.mediumGenerateReport(this.corpMediumAdvice.value)
    .then(resp=>{
      this.createDelay();
      this.showWait=false;
      this.adviceMessage="payNow";
    })
    .catch(err=>{
      this.message=err.message
    })
  }

  corpGenerateReport(){
    this.showWait=true;
    this.investAvenueService.corpGenerateReport(this.corpMediumAdvice.value)
    .then(resp=>{
      this.createDelay();
      this.showWait=false;
      this.adviceMessage="payNow";
    })
    .catch(err=>{
      this.message=err.message
    })
  }

  createDelay(){      
     this.start = new Date().getTime();      
     this.end = this.start;      
    while(this.end < this.start + 5000) {      
        this.end = new Date().getTime();      
    }      
}

  getAdvice(){
    if(this.showForm==false){
   this.showForm=true;
    }
  }

  showUpdateForm(){
    if( this.showUpdate==false){
      this.showUpdate=true;
    }
  }
}
