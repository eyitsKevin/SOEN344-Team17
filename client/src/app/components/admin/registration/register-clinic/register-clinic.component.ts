import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";


@Component({
  selector: 'app-register-clinic',
  templateUrl: './register-clinic.component.html',
  styleUrls: ['./register-clinic.component.scss']
})
export class RegisterClinicComponent implements OnInit {

  
  clinics;
  clinicRegistration: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getAllClinics();
    this.clinicRegistration = this.formBuilder.group({
      name: ["", Validators.required],
      rooms: ["", Validators.required],
      openTime: ["", Validators.required],
      closeTime: ["", Validators.required]
  });

  }

  getAllClinics() {
    this.http.get("http://localhost:8080/clinics/view").subscribe(data => {
      this.clinics = [];
      for (var i in data) {
          let clinic = {
            id: data[i]["id"],
            name: data[i]["name"]
          };
        this.clinics.push(clinic);
      }
    });
  }

  onSubmit() {
  if (this.clinicRegistration.valid) {
    const patient = {
     ...this.clinicRegistration.value
    };
    this.http.post("http://localhost:8080/patients/registration", patient)
      .subscribe(data => {
        this.openSnackBar(data["message"], "Close");
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
