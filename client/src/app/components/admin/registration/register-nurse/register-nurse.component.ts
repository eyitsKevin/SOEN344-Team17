import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-register-nurse',
  templateUrl: './register-nurse.component.html',
  styleUrls: ['./register-nurse.component.scss']
})
export class RegisterNurseComponent implements OnInit {

  clinics;
  nurseRegistration: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getAllClinics();
    this.nurseRegistration = this.formBuilder.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      accessId: ["", [Validators.required, Validators.pattern("^[a-zA-Z]{3}[0-9]{5}$")]],
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
  if (this.nurseRegistration.valid) {
    const nurse = {
     ...this.nurseRegistration.value
    };
    this.http.post("http://localhost:8080/admin/register/nurse", nurse)
    .subscribe(data => {
      if (data == true) {
        this.openSnackBar("Registered", "Close");
      }
      else {
        this.openSnackBar("Error", "Close");
      }
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
