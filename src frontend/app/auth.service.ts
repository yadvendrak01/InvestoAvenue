import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

  constructor() { }

  logout():void{
    sessionStorage.setItem('counter','0');
    sessionStorage.setItem('isLoggedIn','false');
    sessionStorage.removeItem('token');
  }

}
