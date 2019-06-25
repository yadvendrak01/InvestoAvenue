import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { AboutUsComponent } from './about-us/about-us.component';
import {ReactiveFormsModule,FormsModule} from '@angular/forms';
import { HttpModule } from "@angular/http";
import { InvestoAvenueService } from './investo-avenue.service';
import { HomeComponent } from './home/home.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { AuthGuard } from './auth.guard';
import { AuthService } from './auth.service';
import { ProfileUpdateComponent } from './profile-update/profile-update.component';
import { AddDetailComponent } from './add-detail/add-detail.component';
import { TeamComponent } from './team/team.component';
import { CalculateInvestmentComponent } from './calculate-investment/calculate-investment.component';
import { PaymentComponent } from './payment/payment.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginRegisterComponent,
    AboutUsComponent,
    HomeComponent,
    FeedbackComponent,
    ProfileUpdateComponent,
    AddDetailComponent,
    TeamComponent,
    CalculateInvestmentComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
  AppRoutingModule,
  ReactiveFormsModule,
  FormsModule,
  HttpModule
  ],
  providers: [InvestoAvenueService,AuthGuard,AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
