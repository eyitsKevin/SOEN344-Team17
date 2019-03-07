import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlatpickrModule } from 'angularx-flatpickr';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCheckboxModule, MatInputModule, MatButtonToggleModule, MatToolbarModule, MatSidenavModule, MatIconModule, MatSelectModule, MatDialogModule, MatMenuModule } from '@angular/material';
import { HeaderComponent } from './components/shared/header/header.component';
import { SidenavComponent } from './components/shared/sidenav/sidenav.component';
import { DoctorComponent } from './components/doctors/doctor/doctor.component';
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
import { DoctorViewAllComponent } from './components/doctors/doctor-view-all/doctor-view-all.component';
import { DoctorCalendarViewComponent } from './components/doctors/doctor-calendar-view/doctor-calendar-view.component';
import { MatSnackBarModule } from "@angular/material";
import { UserProfileComponent } from './components/shared/user-profile/user-profile.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import {MatBadgeModule} from '@angular/material/badge';
import { PatientBookingComponent} from './components/patients/patient-booking/patient-booking.component';
import {PatientViewAvailabilityComponent} from './components/patients/patient-view-availability/patient-view-availability.component';
import {PatientCartComponent} from './components/patients/patient-cart/patient-cart/patient-cart.component';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DoctorViewAllComponent,
    SidenavComponent,
    DoctorComponent,
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
    PatientBookingComponent,
    PatientViewAvailabilityComponent
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
    MatSidenavModule,
    MatIconModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatBadgeModule,
    MatSelectModule,
    NgbModalModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
    MatListModule,
    MatCardModule
  ],
  bootstrap: [AppComponent],
  entryComponents: [PatientBookingComponent],
  exports: [DoctorCalendarViewComponent],
  providers: [MatDatepickerModule]
})
export class AppModule { }
