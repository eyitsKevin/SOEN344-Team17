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
      private http: HttpClient,
      public dialogRef: MatDialogRef<PatientUpdateConfirmationComponent>,
      @Inject(MAT_DIALOG_DATA) public data) {
        this.data.new.startTime = this.data.new.startTime.replace('T', ' ');
      }

      updateAppointment() {
      this.http.post('http://localhost:8080/appointment/update', this.data.old, this.data.id)
      .subscribe(data => {
      },
        error => { console.log(error); }
      );
      this.dialogRef.close();
    }

    close() {
      this.dialogRef.close();
    }
}
