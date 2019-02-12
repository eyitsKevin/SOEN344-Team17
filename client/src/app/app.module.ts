import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Material UI
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule} from '@angular/material';
import { HeaderComponent } from './components/shared/header/header.component';
import { SidenavComponent } from './components/shared/sidenav/sidenav.component';
import { DoctorComponent } from './components/doctors/doctor/doctor.component';
import { PatientComponent } from './components/patients/patient/patient.component';
import { LoginComponent } from './components/login-registration/login/login.component';
import { NurseComponent } from './components/nurses/nurse/nurse.component';
import { RegistrationComponent } from './components/login-registration/registration/registration.component';
import { SiteComponent } from './components/shared/site/site.component';
import { LoggedInDirective } from './directives/logged-in.directive';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidenavComponent,
    DoctorComponent,
    PatientComponent,
    LoginComponent,
    NurseComponent,
    RegistrationComponent,
    SiteComponent,
    LoggedInDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
