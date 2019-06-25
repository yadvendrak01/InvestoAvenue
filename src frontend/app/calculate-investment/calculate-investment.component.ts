import { Component, OnInit } from '@angular/core';
import { Clients } from '../clients';
import { FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { FormBuilder } from '@angular/forms';
import { InvestoAvenueService } from '../investo-avenue.service';
import { Router } from '@angular/router';

@Component({
  selector: 'calculate-investment',
  templateUrl: './calculate-investment.component.html',
  styleUrls: ['./calculate-investment.component.css']
})
export class CalculateInvestmentComponent implements OnInit {
 client:Clients;
 carDetailForm:FormGroup;
 homeDetailForm:FormGroup;
 educationDetailForm:FormGroup;

 showBox:boolean;
 showBox1:boolean;
 carValue:number;
 homeValue:number;
 educationValue:number;
 retireValue:number;
 constructor( private authService:AuthService,private fb:FormBuilder,private investAvenueService:InvestoAvenueService,private router:Router) {
}

  ngOnInit() {
    this.showBox=false;
    this.showBox1=false;
    this.carValue=null;
    this.homeValue=null;
    this.educationValue=null;
    this.retireValue=null;
    this.client=JSON.parse(sessionStorage.getItem('token'));

    if(this.client.detail==0){
      alert("Please add Details first.");
      this.router.navigate(['add-detail'])
    }

    this.carDetailForm=this.fb.group({
      clients:[this.client],
      time:["",[Validators.required,Validators.min(0)]],
      price:["",[Validators.required,Validators.min(0)]],
      resale:["",[Validators.required,Validators.min(0)]],
      percent:["",[Validators.required,Validators.min(0),Validators.max(20)]]
    })

    this.homeDetailForm=this.fb.group({
      clients:[this.client],
      time:["",[Validators.required,Validators.min(0)]],
      price:["",[Validators.required,Validators.min(0)]],
      resale:["",[Validators.required,Validators.min(0)]],
      percent:["",[Validators.required,Validators.min(0),Validators.max(20)]]
   
    })
    this.educationDetailForm=this.fb.group({
      clients:[this.client],
      age:["",[Validators.required,Validators.min(0)]],
      fee:["",[Validators.required,Validators.min(0)]],
      percent:["",[Validators.required,Validators.min(0),Validators.max(20)]]
    })
    this.carDetailForm.get("resale").setValue(0);
    this.homeDetailForm.get("resale").setValue(0);
  }
  carDetailCalculate(){
    this.client=JSON.parse(sessionStorage.getItem('token'));
    this.investAvenueService.calculateCar(this.carDetailForm.value)
    .then(resp=>{
      this.carValue=resp;
    })
    .catch(err=>{
      alert("Please add valid details.")
    })
  }
  homeDetailCalculate(){
    this.client=JSON.parse(sessionStorage.getItem('token'));
    this.investAvenueService.calculateCar(this.homeDetailForm.value)
    .then(resp=>{
      this.homeValue=resp;
    })
    .catch(err=>{
      alert("Please add valid details.")
    })
  }
  educationDetailCalculate(){
    this.client=JSON.parse(sessionStorage.getItem('token'));
    this.investAvenueService.calculateEducation(this.educationDetailForm.value)
    .then(resp=>{
      this.educationValue=resp;
    })
    .catch(err=>{
      alert("Please add valid details.")
    })
  }
  retireDetailCalculate(){
    this.client=JSON.parse(sessionStorage.getItem('token'));
    this.investAvenueService.calculateRetire(this.client)
    .then(resp=>{
      this.retireValue=resp;
    })
    .catch(err=>{
      alert("Please add valid details.")
    })
  }

  getBoxCar(){
    this.showBox=!this.showBox;
    if(this.carDetailForm.get('resale').value==false){
      this.carDetailForm.get("resale").setValue(0);
    }
  }
  getBoxHome(){
    this.showBox1=!this.showBox1;
    if(this.homeDetailForm.get('resale').value==false){
      this.homeDetailForm.get("resale").setValue(0);
    }
  }

}
