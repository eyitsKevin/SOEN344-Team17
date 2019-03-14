import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';
import { CartDataService } from '../../../services/cart-data.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user;
  authenticated;
  cart = [];

  constructor(private authenticationService: AuthenticationService,
    private cartDataService: CartDataService) { }

  ngOnInit() {
    this.authenticationService.user.subscribe(user => this.user = user);
    this.authenticationService.authenticated.subscribe(authenticated => this.authenticated = authenticated);
    if (this.cartDataService.getAllAppointments() !== null) {
      this.cart = this.cartDataService.getAllAppointments();
    }

  }

}
