import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';
import { CartDataService } from '../../../services/cart-data.service';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  authenticated;

  constructor(private authenticationService: AuthenticationService, private router: Router, private http: HttpClient, private cart: CartDataService) { }

  ngOnInit() {
    this.authenticationService.authenticated.subscribe(authenticated => this.authenticated = authenticated);
  }

  logout() {
    this.authenticationService.changeAuthentication(null);
    this.authenticationService.changeUser(null);
    this.cart.list = [];
    localStorage.setItem("cart", "[]");
    this.router.navigate(['/login']);
  }
}
