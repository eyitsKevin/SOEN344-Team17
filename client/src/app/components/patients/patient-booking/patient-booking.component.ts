import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

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
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    }

  close() {
    this.dialogRef.close();
  }

}
