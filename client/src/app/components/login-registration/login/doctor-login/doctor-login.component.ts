import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-doctor-login',
  templateUrl: './doctor-login.component.html',
  styleUrls: ['./doctor-login.component.scss']
})
export class DoctorLoginComponent implements OnInit {

  doctorLogin: FormGroup;

  constructor(private formBuilder: FormBuilder, private ref: ChangeDetectorRef) { }

  ngOnInit() {
    
    this.doctorLogin = this.formBuilder.group({
      permitNum: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {

  }

}
