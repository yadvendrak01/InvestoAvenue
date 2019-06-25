import { Component, OnInit } from '@angular/core';
import { Clients } from '../clients';
import { Router, UrlHandlingStrategy } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { InvestoAvenueService } from '../investo-avenue.service';

@Component({
  selector: 'payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor(private route:Router,private investoAvenue:InvestoAvenueService) { }
  today:any;
  fileUrl:string;
  recipientId:any;
  client:Clients;

  ngOnInit() {
    this.client=JSON.parse(sessionStorage.getItem('token'));
    this.today=Date.now();
    this.recipientId=Math.random()*100000;
  }

  payNow(){
    this.route.navigate(['/home']);
  }


}
