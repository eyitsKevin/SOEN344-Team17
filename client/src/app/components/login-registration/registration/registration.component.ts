import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  patientRegistration: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {
    
    this.patientRegistration = this.formBuilder.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      healthCard: ["", [Validators.required, Validators.pattern("^[A-Z,a-z]{4}([0-9]{8})$")]],
      birthday: ["", Validators.required],
      gender: ["", Validators.required],
      phone: ["", [Validators.required, Validators.pattern("^[a-z0-9_-]{8,15}$")]],
      email: ["", [Validators.required, Validators.pattern("^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$")]],
      address: ["", [Validators.required]],
      password: ["", Validators.required],
      matchingPassword: ["", Validators.required]
  });

  }

  cancel() {
    this.router.navigate(['']);
  }

  onSubmit() {
  if (this.patientRegistration.valid) {
    const patient = {
     ...this.patientRegistration.value
    };
    this.http.post("http://localhost:8080/patients/registration", patient)
      .subscribe(data => {
        //this.openSnackBar(data[0], "Close");
        this.router.navigate(['']);
      },
      error => { this.openSnackBar(error.error, "Close"); }
      );
  }
  
}

openSnackBar(message: string, action: string) {
  this.snackBar.open(message , action, {
    duration: 5000,
  });
}

}


