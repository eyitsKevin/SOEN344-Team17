import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from "@angular/material";

@Component({
    selector: 'patient-update-confirmation',
    templateUrl: 'patient-update-confirmation.component.html',
    styleUrls: ['patient-update-confirmation.component.scss']
})
export class PatientUpdateConfirmationComponent {

    day;
    start;
    end;

    constructor(
      public dialogRef: MatDialogRef<PatientUpdateConfirmationComponent>,
      @Inject(MAT_DIALOG_DATA) public data) {
        this.data.startTime = this.data.startTime.replace('T', ' ');
      }

    bookAppointment() {
      this.dialogRef.close();
    }

    close() {
      this.dialogRef.close();
    }
}
