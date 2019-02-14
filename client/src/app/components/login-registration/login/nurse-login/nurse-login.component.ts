import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nurse-login',
  templateUrl: './nurse-login.component.html',
  styleUrls: ['./nurse-login.component.scss']
})
export class NurseLoginComponent implements OnInit {

  nurseLogin: FormGroup;
  authenticated;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    
    this.nurseLogin = this.formBuilder.group({
      nurseID: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {
    this.login();
  }

  login() {
    console.log("hi")
    this.authenticationService.changeAuthentication("nurse");
    this.router.navigate(['nurse']);
  }

}
