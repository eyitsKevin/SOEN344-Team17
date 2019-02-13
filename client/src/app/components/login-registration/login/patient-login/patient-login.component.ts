import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-login',
  templateUrl: './patient-login.component.html',
  styleUrls: ['./patient-login.component.scss']
})
export class PatientLoginComponent implements OnInit {

  patientLogin: FormGroup;
  register = false;
  authenticated;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    
    this.patientLogin = this.formBuilder.group({
      healthNum: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {
    this.login();
  }

  registerPatient() {
    this.register = true;
  }

  login(){
    this.authenticationService.changeAuthentication("patient");
    this.router.navigate(['patient']);
  }

  }


