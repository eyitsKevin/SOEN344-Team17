import { AuthenticationService } from './../../../services/authentication.service';
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
    user;

    constructor(
      private http: HttpClient,
      public dialogRef: MatDialogRef<PatientUpdateConfirmationComponent>,
      @Inject(MAT_DIALOG_DATA) public data,
      private authenticationService: AuthenticationService
      ) {}

      updateAppointment() {
      this.authenticationService.user.subscribe(user => this.user = user);
      const patientAppointment = {
        appointmentId: this.data.old,
        patient: this.user,
        cart: [this.data[0]]
      };
      this.http.post('http://localhost:8080/appointment/update', patientAppointment)
      .subscribe(data => {
        this.dialogRef.close(patientAppointment);
      },
        error => { console.log(error); }
      );
    }
    close() {
      this.dialogRef.close();
    }
}
