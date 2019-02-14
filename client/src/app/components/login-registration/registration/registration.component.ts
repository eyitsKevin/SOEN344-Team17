import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  patientRegistration: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    
    this.patientRegistration = this.formBuilder.group({
      healthNum: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      birthday: ["", Validators.required],
      gender: ["", Validators.required],
      phone: ["", [Validators.required, Validators.pattern("^[a-z0-9_-]{8,15}$")]],
      email: ["", [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
      address: ["", [Validators.required]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {
    this.router.navigate(['']);
  }

  cancel() {
    this.router.navigate(['']);
  }

}
