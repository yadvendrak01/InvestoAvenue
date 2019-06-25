import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { InvestoAvenueService } from './investo-avenue.service';
import { AuthService } from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate {
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
       
        if( sessionStorage.getItem("isLoggedIn")==null||sessionStorage.getItem('isLoggedIn')=="false"){
            alert("Please Login to continue !")
            this.router.navigate(['/login-register'])
            return false;
        }
        else{
            
            return true;
        }
    }
    constructor(private router : Router,private authService:AuthService){}

}