import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { InvestoAvenueService } from '../investo-avenue.service';
import { Router } from '@angular/router';
import { FormGroup,Validators } from '@angular/forms';
import { DateValidatar } from '../login-register/date.validator';
import { Clients } from '../clients';
import { AuthService } from '../auth.service';

@Component({
  selector: 'profile-update',
  templateUrl: './profile-update.component.html',
  styleUrls: ['./profile-update.component.css']
})
export class ProfileUpdateComponent implements OnInit {

  constructor(private fb:FormBuilder,private investAvenueService:InvestoAvenueService,private router:Router,public authService:AuthService) { }
  updateForm:FormGroup;
  successMessage:string;
  errorMessage:string;
  client:Clients;
  name:string;
  dob:Date;
  mail:string;
  phone:number;
  ngOnInit() {

    this.client=this.client=JSON.parse(sessionStorage.getItem('token'));

    this.updateForm=this.fb.group({
      name:[this.client.name,[Validators.required,Validators.minLength(4)]],
      typeOfClient:[""],
      password:[""],
      contactNo:[this.client.contactNo,[Validators.required,Validators.pattern("[1-9]([0-9]){9}")]],
      email:[this.client.email,[Validators.required,Validators.pattern("[A-Za-z][\\w0-9]+@[A-Za-z]+\\.[A-Za-z]+")]],
      cdate:[""],
      userId:[""],
      detail:[""]
    });


  }
  update(){
    this.updateForm.get("userId").setValue(this.client.userId);
    this.updateForm.get("typeOfClient").setValue(this.client.typeOfClient);
    this.updateForm.get("password").setValue(this.client.password);
    this.updateForm.get("cdate").setValue(this.client.cdate);
    this.updateForm.get("detail").setValue(this.client.detail)
    this.investAvenueService.update(this.updateForm.value)
    .then(resp=>{
      this.successMessage=resp.message;
      alert("Details Updated successfully.");
      sessionStorage.setItem('token',JSON.stringify(resp));
      sessionStorage.setItem('counter','0');
      this.router.navigate(['/']);
   })
    .catch( err=>{
      this.errorMessage=err.message
     })

  }

}
