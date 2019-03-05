import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login-registration/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SiteComponent } from './components/shared/site/site.component';
import { PatientComponent } from './components/patients/patient/patient.component';
import { NurseComponent } from './components/nurses/nurse/nurse.component';
import { ThisWeekComponent } from './components/doctors/this-week/this-week.component';
import { UserProfileComponent } from './components/shared/user-profile/user-profile.component';
import { DoctorAuthenticationGuard } from './guards/doctor-authentication.guard';
import { PatientAuthenticationGuard } from './guards/patient-authentication.guard';
import { NurseAuthenticationGuard } from './guards/nurse-authentication.guard';
import { DoctorCalendarViewComponent } from './components/doctors/doctor-calendar-view/doctor-calendar-view.component'
import { PatientViewAvailabilityComponent } from './components/patients/patient-view-availability/patient-view-availability.component';

const routes: Routes = [
  {path: '', component: SiteComponent,
  //will need guards
  children: [
    { path: 'patient', canActivate: [PatientAuthenticationGuard], component: PatientComponent},
    { path: 'patient/book/walkin', canActivate: [PatientAuthenticationGuard], component: PatientViewAvailabilityComponent},
    { path: 'patient/book/annual', canActivate: [PatientAuthenticationGuard], component: PatientViewAvailabilityComponent},
    { path: 'doctor', canActivate: [DoctorAuthenticationGuard], component: ThisWeekComponent},
    { path: 'doctor/availabilities', component: DoctorCalendarViewComponent },
    { path: 'nurse', canActivate: [NurseAuthenticationGuard], component: NurseComponent},
    { path: 'user-profile', component: UserProfileComponent}
  ]},
  {path: 'login', component: LoginComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
