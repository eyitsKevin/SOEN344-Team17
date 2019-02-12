import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login-registration/login/login.component';
import { SiteComponent } from './components/shared/site/site.component';
import { PatientComponent } from './components/patients/patient/patient.component';
import { DoctorComponent } from './components/doctors/doctor/doctor.component';
import { NurseComponent } from './components/nurses/nurse/nurse.component';

const routes: Routes = [
  {path: '', component: SiteComponent,
  //will need guards
  children: [
    { path: '', component: PatientComponent},
    { path: '', component: DoctorComponent},
    { path: '', component: NurseComponent}
  ]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
