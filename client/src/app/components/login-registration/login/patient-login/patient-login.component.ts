import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-patient-login',
  templateUrl: './patient-login.component.html',
  styleUrls: ['./patient-login.component.scss']
})
export class PatientLoginComponent implements OnInit {

  patientLogin: FormGroup;
  register = false;

  constructor(private formBuilder: FormBuilder, private ref: ChangeDetectorRef) { }

  ngOnInit() {
    
    this.patientLogin = this.formBuilder.group({
      healthNum: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {

  }

  registerPatient() {
    this.register = true;
  }

  }


