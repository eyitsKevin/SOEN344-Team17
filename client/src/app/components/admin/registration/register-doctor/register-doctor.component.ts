import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-register-doctor',
  templateUrl: './register-doctor.component.html',
  styleUrls: ['./register-doctor.component.scss']
})
export class RegisterDoctorComponent implements OnInit {

  clinics;
  doctorRegistration: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getAllClinics();
    this.doctorRegistration = this.formBuilder.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      permitNumber: ["", [Validators.required, Validators.pattern("^[0-9]{7}$")]],
      city: ["", Validators.required],
      specialty: ["", Validators.required],
      clinicId: ["", Validators.required],
      password: ["", Validators.required]
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
  if (this.doctorRegistration.valid) {
    const patient = {
     ...this.doctorRegistration.value
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
