import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-login',
  templateUrl: './doctor-login.component.html',
  styleUrls: ['./doctor-login.component.scss']
})
export class DoctorLoginComponent implements OnInit {

  doctorLogin: FormGroup;
  authenticated;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {

    this.doctorLogin = this.formBuilder.group({
      permitNum: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      password: ["", Validators.required]
    });

  }

  onSubmit() {
    this.login();
  }

  login() {
    this.authenticationService.changeAuthentication("doctor");
    this.router.navigate(['doctor']);
  }

}
