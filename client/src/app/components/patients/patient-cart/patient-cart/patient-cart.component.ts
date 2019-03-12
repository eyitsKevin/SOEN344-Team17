import { Component, OnInit } from '@angular/core';
import { CartDataService } from '../../../../services/cart-data.service';
import { AuthenticationService } from '../../../../services/authentication.service';
import { MatSnackBar } from '@angular/material';
import { MatDialog } from '@angular/material';
import { PatientPaymentComponent } from '../../patient-payment/patient-payment.component';

@Component({
  selector: 'app-patient-cart',
  templateUrl: './patient-cart.component.html',
  styleUrls: ['./patient-cart.component.css']
})
export class PatientCartComponent implements OnInit {

  constructor(private cartDataService: CartDataService,
    private authenticationService: AuthenticationService,
    public snackBar: MatSnackBar,
    public dialog: MatDialog
  ) { }
  cart = [];
  patient;

  openDialog(): void {
    this.authenticationService.user.subscribe(patient => this.patient = patient);
    const patientWithcart = {
      patient: this.patient,
      cart: this.cart
    };
    const dialogRef = this.dialog.open(PatientPaymentComponent, {
      width: '500px',
      height: '500px',
      data: patientWithcart
    });
  }

  ngOnInit() {

    this.cart = this.cartDataService.getAllAppointments();


    for (let i = 0; i < this.cart.length; i++) {
      const fullDate = this.cart[i].startTime.split('T');
      const fullDate2 = this.cart[i].endTime.split('T');
      this.cart[i].day = fullDate[0];
      this.cart[i].start = fullDate[1];
      this.cart[i].end = fullDate2[1];
    }
  }

  remove(index) {
    this.cartDataService.removeAppointment(index);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }
}
