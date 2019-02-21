import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-nurse-login',
  templateUrl: './nurse-login.component.html',
  styleUrls: ['./nurse-login.component.scss']
})
export class NurseLoginComponent implements OnInit {

  nurseLogin: FormGroup;
  authenticated;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {
    
    this.nurseLogin = this.formBuilder.group({
      accessId: ["", [Validators.required, Validators.pattern("^[a-zA-Z]{3}[0-9]{5}$")]],
      password: ["", Validators.required]
  });

  }

  onSubmit() {
    if (this.nurseLogin.valid) {
      const nurse = {
        ...this.nurseLogin.value
      };
      this.http.post("http://localhost:8080/nurses/login", nurse)
        .subscribe(data => {
          this.login(data);
        },
          error => { console.log(error); this.openSnackBar(error.error, "Close"); }
        );
    }
  }

  login(data) {
    this.authenticationService.changeAuthentication("nurse");
    this.authenticationService.changeUser(data);
    localStorage.setItem("authentication", "nurse");
    localStorage.setItem("user", JSON.stringify(data));
    this.router.navigate(['nurse']);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
