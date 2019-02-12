import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-nurse-login',
  templateUrl: './nurse-login.component.html',
  styleUrls: ['./nurse-login.component.scss']
})
export class NurseLoginComponent implements OnInit {

  nurseLogin: FormGroup;

  constructor(private formBuilder: FormBuilder, private ref: ChangeDetectorRef) { }

  ngOnInit() {
    
    this.nurseLogin = this.formBuilder.group({
      nurseID: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {

  }

}
