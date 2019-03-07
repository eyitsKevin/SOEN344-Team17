import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from "@angular/material";
export interface DialogData {

}

@Component({
  selector: 'app-patient-cancel',
  templateUrl: './patient-cancel.component.html',
  styleUrls: ['./patient-cancel.component.css']
})
export class PatientCancelComponent {

  constructor(public dialogRef: MatDialogRef<PatientCancelComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public snackBar: MatSnackBar,
    private http: HttpClient
    ) {}

  ngOnInit() {
    console.log(this.data);
  }

  close(){
    this.dialogRef.close();
  }

  cancelAppointment(){
    this.dialogRef.close();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }
}
