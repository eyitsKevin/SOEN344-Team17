import {Component, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { CartDataService } from '../../../services/cart-data.service';
export interface DialogData {

}

@Component({
  selector: 'app-patient-booking',
  templateUrl: './patient-booking.component.html',
  styleUrls: ['./patient-booking.component.css']
})
export class PatientBookingComponent {

  constructor(
    public dialogRef: MatDialogRef<PatientBookingComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private cartDataService: CartDataService) {
  }

  bookAppointment() {
    this.cartDataService.addAppointment(this.data);
    this.dialogRef.close();
  }

  close() {
    this.dialogRef.close();
  }

}
