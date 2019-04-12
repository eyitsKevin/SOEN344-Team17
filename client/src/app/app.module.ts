import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlatpickrModule } from 'angularx-flatpickr';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatInputModule,
  MatButtonToggleModule,
  MatToolbarModule,
  MatSidenavModule,
  MatIconModule,
  MatSelectModule,
  MatDialogModule,
  MatMenuModule,
  MatAutocompleteModule,
  MatCardModule,
  MatBottomSheetModule,
  MatSlideToggleModule,
  MatListModule,
  MatExpansionModule,
  MAT_BOTTOM_SHEET_DATA,
  MatBadgeModule,
} from '@angular/material';
import { HeaderComponent } from './components/shared/header/header.component';
import { SidenavComponent } from './components/shared/sidenav/sidenav.component';
import { PatientComponent } from './components/patients/patient/patient.component';
import { LoginComponent } from './components/login-registration/login/login.component';
import { NurseComponent } from './components/nurses/nurse/nurse.component';
import { RegistrationComponent } from './components/login-registration/registration/registration.component';
import { SiteComponent } from './components/shared/site/site.component';
import { LoggedInDirective } from './directives/logged-in.directive';
import { PatientLoginComponent } from './components/login-registration/login/patient-login/patient-login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DoctorLoginComponent } from './components/login-registration/login/doctor-login/doctor-login.component';
import { NurseLoginComponent } from './components/login-registration/login/nurse-login/nurse-login.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { DoctorCalendarViewComponent } from './components/doctors/doctor-calendar-view/doctor-calendar-view.component';
import { MatSnackBarModule } from '@angular/material';
import { UserProfileComponent } from './components/shared/user-profile/user-profile.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { PatientBookingComponent} from './components/patients/patient-booking/patient-booking.component';
import {PatientViewAvailabilityComponent} from './components/patients/patient-view-availability/patient-view-availability.component';
import { NurseBookingComponent } from './components/nurses/nurse-booking/nurse-booking.component';
import { NursePatientBookingComponent } from './components/nurses/nurse-patient-booking/nurse-patient-booking.component';
import { PatientCartComponent } from './components/patients/patient-cart/patient-cart.component';
import { PatientCancelComponent } from './components/patients/patient-cancel/patient-cancel.component';
import { PatientPaymentComponent } from './components/patients/patient-payment/patient-payment.component';
import { UpcomingAppointmentsComponent } from './components/doctors/upcoming-appointments/upcoming-appointments.component';
import { PatientUpdateComponent } from './components/patients/patient-update/patient-update.component';
import { PatientUpdateConfirmationComponent } from './components/patients/patient-update-confirmation/patient-update-confirmation.component';
import { AdminLoginComponent } from './components/admin/login/admin-login.component';
import { RegisterDoctorComponent } from './components/admin/registration/register-doctor/register-doctor.component';
import { RegisterNurseComponent } from './components/admin/registration/register-nurse/register-nurse.component';
import { RegisterClinicComponent } from './components/admin/registration/register-clinic/register-clinic.component';
import { AdminComponent } from './components/admin/admin/admin.component';
import { AdminRegistrationComponent } from './components/admin/registration/admin-registration.component';
import { ClinicProfileComponent } from './components/clinics/clinic-profile/clinic-profile.component';
import { ClinicComponent } from './components/clinics/clinic/clinic.component';
import { MatTreeModule } from '@angular/material/tree';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidenavComponent,
    PatientComponent,
    LoginComponent,
    NurseComponent,
    PatientCartComponent,
    RegistrationComponent,
    SiteComponent,
    LoggedInDirective,
    PatientLoginComponent,
    DoctorLoginComponent,
    NurseLoginComponent,
    DoctorCalendarViewComponent,
    UserProfileComponent,
    NotFoundComponent,
    UpcomingAppointmentsComponent,
    PatientBookingComponent,
    PatientViewAvailabilityComponent,
    NurseBookingComponent,
    NursePatientBookingComponent,
    PatientCancelComponent,
    PatientPaymentComponent,
    PatientUpdateComponent,
    PatientUpdateConfirmationComponent,
    AdminLoginComponent,
    RegisterDoctorComponent,
    RegisterNurseComponent,
    RegisterClinicComponent,
    AdminComponent,
    AdminRegistrationComponent,
    ClinicProfileComponent,
    ClinicComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FlatpickrModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    MatCheckboxModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatButtonToggleModule,
    MatToolbarModule,
    MatDialogModule,
    MatListModule,
    MatSidenavModule,
    MatIconModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatBadgeModule,
    MatSelectModule,
    NgbModalModule,
    MatAutocompleteModule,
    MatCardModule,
    MatBottomSheetModule,
    MatSlideToggleModule,
    MatListModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    MatListModule,
    MatCardModule,
    MatTreeModule
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    PatientBookingComponent,
    PatientPaymentComponent,
    PatientCancelComponent,
    NursePatientBookingComponent,
    PatientUpdateComponent,
    PatientUpdateConfirmationComponent,
    NurseBookingComponent,
    PatientViewAvailabilityComponent
  ],
  exports: [DoctorCalendarViewComponent, NursePatientBookingComponent],
  providers: [
    MatDatepickerModule,
    NurseBookingComponent,
    PatientViewAvailabilityComponent,
    {provide: MAT_BOTTOM_SHEET_DATA, useValue: {}}]
})
export class AppModule { }
