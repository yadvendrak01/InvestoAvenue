import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { InvestoAvenueService } from '../investo-avenue.service';
import { DateValidatar } from './date.validator';
import { error } from 'util';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
  selector: 'login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.css']
})
export class LoginRegisterComponent implements OnInit {
  
  eyes="open";
  eyes2="open"
  type2="password"
  type="password";
  registerForm:FormGroup;
  loginForm:FormGroup;
  toShowDate:string;
  toShowFirm:string;
  toShowOthers:boolean;
  toShowLogin:boolean;
  toShowRegister:boolean;
  successMessage:string;
  errorMessage:string;
  userIdMessage:string;
  userIdBool:boolean; 
  returnUrl:string;
  // token:Clients;
  constructor(public route:Router, private authService:AuthService,private fb:FormBuilder,private investAvenueService:InvestoAvenueService,private router:Router) {
  }
  login(){
    this.successMessage=null;
    this.errorMessage=null;
    
    this.investAvenueService.login(this.loginForm.value)
    .then((resp)=>{
      // alert(JSON.stringify(resp))
      this.successMessage=resp.message;
      sessionStorage.setItem('isLoggedIn','true');
      sessionStorage.setItem('token',JSON.stringify(resp))
      sessionStorage.setItem('counter','0');
      this.router.navigate(['/']);    
    })
    .catch((err)=>{
      this.errorMessage=err.message})
 }
 register(){
   this.successMessage=null;
   this.errorMessage=null;
   this.investAvenueService.register(this.registerForm.value)
   .then(resp=>{
     this.successMessage=resp.message
     this.toToggleRegisterLogin();
     alert("You are Registered Successfully! Please Login Again!");

   })
   .catch( err=>{
     this.errorMessage=err.message
    })
 }
  getUserId(){
    if(this.registerForm.controls.userId.valid){
      this.investAvenueService.userIdVerify(this.registerForm.get("userId").value)
    .then(resp=>{
      this.userIdMessage=resp.message;
      if(this.userIdMessage=="User Id is available."){
        this.userIdBool=false;
      }else{
        this.userIdBool=true;
      }
    })
    .catch((err)=>{
      this.userIdBool=false;
      this.userIdMessage=err.message
    })
}
}
  forDate(){
    if(this.registerForm.get('typeOfClient').value=="individual"){
      this.toShowFirm="";
      this.toShowDate="Date of Birth ";
      this.toShowOthers=false;
    }else if(this.registerForm.get('typeOfClient').value==undefined){
        this.toShowOthers=true;
    }
    else {
      this.toShowOthers=false;
      this.toShowFirm="Firm "
      this.toShowDate="Firm Founded On ";
    }
  }

  toToggleRegisterLogin(){
    this.toShowRegister=!this.toShowRegister;
    this.toShowLogin=!this.toShowLogin;
  }

 

  ngOnInit() {

    if(sessionStorage.getItem('isLoggedIn')=="true"){
      alert("User is already logged In. Please Log out !");
      this.route.navigate(['/home'])
    }
    this.userIdBool=true;
    this.toShowFirm="";
    this.toShowDate="Date of Birth ";
    this.toShowOthers=false;
    this.toShowLogin=true;
    this.toShowRegister=false;
    this.registerForm=this.fb.group({
      name:["",[Validators.required,Validators.minLength(4)]],
      typeOfClient:["",Validators.required],
      password:["",[Validators.required,Validators.pattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,12}$")]],
      contactNo:["",[Validators.required,Validators.pattern("[1-9]([0-9]){9}")]],
      email:["",[Validators.required,Validators.pattern("[A-Za-z][\\w0-9]+@[A-Za-z]+\\.[A-Za-z]+")]],
      cdate:["",[Validators.required,DateValidatar.dateValidate]],
      userId:["",[Validators.required,Validators.minLength(8)]]
    })
    this.loginForm=this.fb.group({
      userId:["",[Validators.required,Validators.minLength(3)]],
      password:["",[Validators.required,Validators.minLength(3)]]
    })

  }



  eye(){
   
    if(this.type=="password")
    {
      this.type="text"
      this.eyes="close";
    }
    else
    {
      this.type="password";
      this.eyes="open";
    }
  } 
  eye2(){
   
    if(this.type2=="password")
    {
      this.type2="text"
      this.eyes2="close";
    }
    else
    {
      this.type2="password";
      this.eyes2="open";
    }
  } 
}
