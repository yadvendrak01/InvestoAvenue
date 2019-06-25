import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { HomeComponent } from './home/home.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { AuthGuard } from './auth.guard';
import { ProfileUpdateComponent } from './profile-update/profile-update.component';
import { AddDetailComponent } from './add-detail/add-detail.component';
import { TeamComponent } from './team/team.component';
import { CalculateInvestmentComponent } from './calculate-investment/calculate-investment.component';
import { PaymentComponent } from './payment/payment.component';

const routes: Routes = [
  {
    path:"login-register",component:LoginRegisterComponent
  },
  {
    path:"about-us",component:AboutUsComponent
  },
  { 
   path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path:"home",component:HomeComponent
  },
  {
    path:"feedback",component:FeedbackComponent,canActivate:[AuthGuard]
  },
  {
    path:"profile-update",component:ProfileUpdateComponent,canActivate:[AuthGuard]
  },
  {
    path:"add-detail",component:AddDetailComponent,canActivate:[AuthGuard]
  },
  {
    path:"team",component:TeamComponent
  },
  {
    path:"calculate-investment",component:CalculateInvestmentComponent,canActivate:[AuthGuard]
  },{
    path:"payment",component:PaymentComponent,canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// children:[
//   {
//     path:"calculate-investment",component:CalculateInvestmentComponent
//   }
// ]
