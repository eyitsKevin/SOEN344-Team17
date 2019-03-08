import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef, MatSnackBar} from '@angular/material';
import {HttpClient} from '@angular/common/http';
import {NurseBookingComponent} from '../nurse-booking/nurse-booking.component';

@Component({
  selector: 'app-nurse-patient-booking',
  templateUrl: './nurse-patient-booking.component.html',
  styleUrls: ['./nurse-patient-booking.component.scss']
})
export class NursePatientBookingComponent implements OnInit {

  @ViewChild(NurseBookingComponent) alert: NurseBookingComponent;


  constructor(private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data: any, private snackBar: MatSnackBar, public dialogRef: MatDialogRef<NursePatientBookingComponent>, private bottomSheet: NurseBookingComponent) {}

  patientCart;

  ngOnInit() {
    const cart = [];
    cart.push(this.data.cart);
    this.patientCart = {
      patient: this.data.patient,
      cart: cart,
    };
  }

  bookAppointment() {
    this.http.post('/api/availability/cart/checkout', this.patientCart)
      .subscribe(() => {
          this.dialogRef.close();
          this.bottomSheet.closeBottomSheet();
          this.openSnackBar('Successfully booked appointment for ' + this.data.patient.firstName , 'Close');
        },
        error => {this.openSnackBar(error.error, 'Close'); }
      );
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
