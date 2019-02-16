import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-doctor-login',
  templateUrl: './doctor-login.component.html',
  styleUrls: ['./doctor-login.component.scss']
})
export class DoctorLoginComponent implements OnInit {

  doctorLogin: FormGroup;
  authenticated;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {

    this.doctorLogin = this.formBuilder.group({
      permitNumber: ["", [Validators.required, Validators.pattern("^[0-9]{7}$")]],
      password: ["", Validators.required]
    });

  }

  onSubmit() {
    if (this.doctorLogin.valid) {
      const patient = {
        ...this.doctorLogin.value
      };
      this.http.post("http://localhost:8080/doctors/login", patient)
        .subscribe(data => {
          this.login(data);
        },
          error => { console.log(error); this.openSnackBar(error.error, "Close"); }
        );
    }
  }

  login(data) {
    this.authenticationService.changeAuthentication("doctor");
    this.authenticationService.changeUser(data);
    this.router.navigate(['doctor']);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
