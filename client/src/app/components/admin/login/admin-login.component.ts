import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from "@angular/material";
import { HttpClient } from "@angular/common/http";
import { AuthenticationService } from '../../../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {

  adminLogin: FormGroup;

  constructor(private formBuilder: FormBuilder,  private router: Router, private authenticationService: AuthenticationService, private http: HttpClient, public snackBar: MatSnackBar) { }

  ngOnInit() {

    this.adminLogin = this.formBuilder.group({
      userName: ["", Validators.required],
      password: ["", Validators.required]
    });

  }

  onSubmit() {
    if (this.adminLogin.valid) {
      const admin = {
        ...this.adminLogin.value
      };
      this.http.post("http://localhost:8080/admin/login", admin)
        .subscribe(data => {
          if (data == true) {
            this.authenticationService.changeAuthentication("admin");
          }
        },
          error => { this.openSnackBar("Incorrect", "Close"); }
        );
    }
  }

  onSubmit2() {
    let user = {"firstName" : "admin"};
    this.authenticationService.changeAuthentication("admin");
    this.authenticationService.changeUser(user);
    localStorage.setItem("authentication", "admin");
    localStorage.setItem("user", JSON.stringify(user));
    this.router.navigate(['admin']);
}

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}

