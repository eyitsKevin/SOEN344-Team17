import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login-registration/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SiteComponent } from './components/shared/site/site.component';
import { PatientComponent } from './components/patients/patient/patient.component';
import { NurseComponent } from './components/nurses/nurse/nurse.component';
import { UpcomingAppointmentsComponent } from './components/doctors/upcoming-appointments/upcoming-appointments.component';
import { UserProfileComponent } from './components/shared/user-profile/user-profile.component';
import { DoctorAuthenticationGuard } from './guards/doctor-authentication.guard';
import { PatientAuthenticationGuard } from './guards/patient-authentication.guard';
import { NurseAuthenticationGuard } from './guards/nurse-authentication.guard';
import { AdminAuthenticationGuard } from './guards/admin-authentication.guard';
import { DoctorCalendarViewComponent } from './components/doctors/doctor-calendar-view/doctor-calendar-view.component';
import { PatientViewAvailabilityComponent } from './components/patients/patient-view-availability/patient-view-availability.component';
import { NurseBookingComponent} from './components/nurses/nurse-booking/nurse-booking.component';
import { PatientCartComponent } from './components/patients/patient-cart/patient-cart.component';
import { AdminLoginComponent } from './components/admin/login/admin-login.component';
import { AdminComponent } from './components/admin/admin/admin.component';
import { AdminRegistrationComponent } from './components/admin/registration/admin-registration.component';

const routes: Routes = [
  {path: '', component: SiteComponent,
    // will need guards
    children: [
      { path: 'patient', canActivate: [PatientAuthenticationGuard], component: PatientComponent},
      { path: 'patient/book/walkin', canActivate: [PatientAuthenticationGuard], component: PatientViewAvailabilityComponent},
      { path: 'patient/book/annual', canActivate: [PatientAuthenticationGuard], component: PatientViewAvailabilityComponent},
      { path: 'doctor', canActivate: [DoctorAuthenticationGuard], component: UpcomingAppointmentsComponent},
      { path: 'doctor/availabilities', component: DoctorCalendarViewComponent },
      { path: 'nurse', canActivate: [NurseAuthenticationGuard], component: NurseComponent},
      { path: 'nurse/book', canActivate: [NurseAuthenticationGuard], component: NurseBookingComponent},
      { path: 'user-profile', component: UserProfileComponent},
      { path: 'cart', canActivate: [PatientAuthenticationGuard], component: PatientCartComponent},
      { path: 'admin', canActivate: [AdminAuthenticationGuard], component: AdminComponent},
      { path: 'admin/register', canActivate: [AdminAuthenticationGuard], component: AdminRegistrationComponent}
    ]},
  {path: 'login', component: LoginComponent},
  {path: 'login/admin', component: AdminLoginComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
