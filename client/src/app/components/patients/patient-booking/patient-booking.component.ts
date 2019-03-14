import { HttpClient } from '@angular/common/http';
import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { CartDataService } from '../../../services/cart-data.service';
export interface DialogData {}

@Component({
  selector: 'app-patient-booking',
  templateUrl: './patient-booking.component.html',
  styleUrls: ['./patient-booking.component.css']
})
export class PatientBookingComponent {

  day;
  start;
  end;

  constructor(
    public dialogRef: MatDialogRef<PatientBookingComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private cartDataService: CartDataService,
    private http: HttpClient
    ) {
      const fullDate =  data['startTime'].split('T');
      const fullDate2 =  data['endTime'].split('T');
      this.day = fullDate[0];
      this.start = fullDate[1];
      this.end = fullDate2[1];
    }

    bookAppointment() {
    this.cartDataService.addAppointment(this.data);
    this.dialogRef.close();
  }

  close() {
    this.dialogRef.close();
  }

}
