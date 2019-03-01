import { Component, OnInit } from '@angular/core';
import { CartDataService } from '../../../../services/cart-data.service';
import { AuthenticationService } from '../../../../services/authentication.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-patient-cart',
  templateUrl: './patient-cart.component.html',
  styleUrls: ['./patient-cart.component.css']
})
export class PatientCartComponent implements OnInit {

  constructor(private cartDataService: CartDataService,
              private authenticationService: AuthenticationService,
              public snackBar: MatSnackBar,
              private http: HttpClient) { }
  cart = [];
  patient;
  healthcard;
  ngOnInit() {
    this.cart = this.cartDataService.getAllAppointments();

    for (let i = 0; i < this.cart.length; i++) {
      const fullDate =  this.cart[i].startTime.split('T');
      const fullDate2 =  this.cart[i].endTime.split('T');
      this.cart[i].day = fullDate[0];
      this.cart[i].start = fullDate[1];
      this.cart[i].end = fullDate2[1];
    }
  }

  remove(index) {
    this.cartDataService.removeAppointment(index);
  }

  checkout() {
    this.authenticationService.user.subscribe(patient => this.patient = patient);
    const patientWithcart = {
      patient: this.patient,
      cart: this.cart
    };
    this.http.post('/api/availability/cart/checkout', patientWithcart)
      .subscribe(data => {

        },
        error => { console.log(error); this.openSnackBar(error.error, 'Close'); }
      );
    this.cart = this.cartDataService.deleteAllAppointments();
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
