import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers } from '@angular/http';
import { Clients } from './clients';
// import "rxjs/add/operator/toPromise"
import { promise } from 'selenium-webdriver';
import { Feedback } from './feedback';
import { environment } from '../environments/environment';
import { UrlSegment } from '@angular/router';

@Injectable()
export class InvestoAvenueService  {


  private headers=new Headers({'Content-Type':'application/json'});
  constructor(private http:Http) { }

  login(data):Promise<Clients>{
    const url="http://localhost:3333/IA_BackEnd/client/login";
   return this.http.post(url,data)
    .toPromise()
    .then((resp)=>{
      return resp.json() as Clients
    })
    .catch(this.handleError);

  }
  
  register(data):Promise<Clients>{
    const url1="http://localhost:3333/IA_BackEnd/client/register";
    //alert(JSON.stringify(data))
   return this.http.post(url1,JSON.stringify(data),{headers:this.headers})
    .toPromise()
    .then((resp)=>{return resp.json() as Clients})
    .catch(this.handleError);

  }

  userIdVerify(data):Promise<Clients>{
    const url1="http://localhost:3333/IA_BackEnd/client/useridverify/"+data;
   return this.http.get(url1)
    .toPromise()
    .then((resp)=>{return resp.json()})
    .catch(this.handleError);

  }

  feedback(data):Promise<Feedback>{
    const url="http://localhost:3333/IA_BackEnd/client/review/";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=>{return resp.json()})
    .catch(this.handleError)
    
  }
  update(data):Promise<Clients>{
    const url="http://localhost:3333/IA_BackEnd/client/update/";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=>{return resp.json()})
    .catch(this.handleError)
    
  }

  clientAddDetails(data):Promise<Clients>{
    const url="http://localhost:3333/IA_BackEnd/client/addIndividualDetails";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=>{return resp.json()})
    .catch(this.handleError)
  }
  
  clientUpdateDetails(data):Promise<Clients>{
    const url="http://localhost:3333/IA_BackEnd/client/updateDetails";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }
  mediumCorpDetails(data):Promise<Clients>{
    const url="http://localhost:3333/IA_BackEnd/client/addCorporateDetails";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=>{return resp.json()})
    .catch(this.handleError)
  }

  getFeedback():Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/feedbackDisplay";
    return this.http.get(url)
    .toPromise()
    .then(resp=>{return resp.json()})
    .catch(this.handleError)
  }

  calculateCar(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/individualCar";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }
  calculateHome(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/individualHome";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }
  calculateEducation(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/individualEdu";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }
  calculateRetire(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/individualRetire";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }
  clientGenerateReport(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/individual";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }

  mediumGenerateReport(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/medium";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }
  corpGenerateReport(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/corporate";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=> resp.json())
    .catch(this.handleError)
  }

  mediumCorporateUpdateDetails(data):Promise<any>{
    const url="http://localhost:3333/IA_BackEnd/client/updateCorporateDetails";
    return this.http.post(url,data)
    .toPromise()
    .then(resp=>resp.json())
    .catch(this.handleError)
  }
  
  handleError(error){
    return Promise.reject(error.json() || error);
  }
}
