import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-patient-cancel',
  templateUrl: './patient-cancel.component.html',
  styleUrls: ['./patient-cancel.component.css']
})
export class PatientCancelComponent {

  constructor(public dialogRef: MatDialogRef<PatientCancelComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
    public snackBar: MatSnackBar,
    private http: HttpClient
    ) {}

  close() {
    this.dialogRef.close();
  }

  cancelAppointment() {
    this.http.post('http://localhost:8080/appointment/cancel', this.data.id)
        .subscribe(data => {
          this.dialogRef.close(this.data.id);
        },
          error => { console.log(error); this.openSnackBar(error.error, 'Close'); }
        );
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  convertTime(time) {
    let newTime = new Date(time);
    newTime.setHours(newTime.getHours() - 5);
    const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
    let formattedTime = newTime.toLocaleDateString('en-US', options);
    return formattedTime;
    }
}
