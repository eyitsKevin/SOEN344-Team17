import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-patient-login',
  templateUrl: './patient-login.component.html',
  styleUrls: ['./patient-login.component.scss']
})
export class PatientLoginComponent implements OnInit {

  patientLogin: FormGroup;
  register = false;
  authenticated;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {

    this.patientLogin = this.formBuilder.group({
      healthCard: ["", [Validators.required, Validators.pattern("^[A-Z,a-z]{4}([0-9]{8})$")]],
      password: ["", Validators.required]
    });

  }

  onSubmit() {
    if (this.patientLogin.valid) {
      const patient = {
        ...this.patientLogin.value
      };
      this.http.post("http://localhost:8080/patients/login", patient)
        .subscribe(data => {
          this.login(data);
        },
          error => { console.log(error); this.openSnackBar(error.error, "Close"); }
        );
    }
  }

  registerPatient() {
    this.register = true;
  }

  login(data) {
    this.authenticationService.changeAuthentication("patient");
    this.authenticationService.changeUser(data);
    localStorage.setItem("authentication", "patient");
    localStorage.setItem("user", JSON.stringify(data));
    this.router.navigate(['patient']);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}


