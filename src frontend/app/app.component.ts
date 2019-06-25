import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { Clients } from './clients';
import { Input } from '@angular/core';
import { OnChanges } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  name:string;
  userId:string;
  client:Clients;
  homeMessage:string;
  constructor(public authService:AuthService,private router:Router){}
  ngOnInit() {
    this.name=null;
    this.client=null;
    this.userId=null;
    this.homeMessage=null;
    if(sessionStorage.getItem('isLoggedIn')=="true"){
      this.client=JSON.parse(sessionStorage.getItem('token'));
      this.name=this.client.name;
      this.userId=this.client.userId;
    }else{
      this.homeMessage="Hello There Please Login";
    }
  }
  logout(){
    if(confirm("Are you sure you want to Exit????")){
        this.authService.logout();
        this.client=null;
        this.name=null;
        this.userId=null
        this.router.navigate(['/']);
  }
    // location.reload();
  }
}
