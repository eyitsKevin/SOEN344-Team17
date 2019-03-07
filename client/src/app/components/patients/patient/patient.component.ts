import { Component, ChangeDetectionStrategy, ViewChild, TemplateRef, OnInit } from '@angular/core';
import {MatDialogModule, MatDialog} from '@angular/material/dialog';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PatientBookingComponent } from '../patient-booking/patient-booking.component';
import { CartDataService } from '../../../services/cart-data.service';
import { AuthenticationService } from '../../../services/authentication.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from "@angular/material";
import { PatientCancelComponent } from '../patient-cancel/patient-cancel.component'

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  constructor( 
    private cartDataService: CartDataService,
    private authenticationService: AuthenticationService,
    public dialog: MatDialog, 
    public snackBar: MatSnackBar,
    private router: Router, 
    private http: HttpClient) {}

    list;
    user;
    healthcard;

  ngOnInit() {
    this.getAppointments()
  }

  getAppointments(){
    this.authenticationService.user.subscribe(user => this.user = user);
    this.http.post("http://localhost:8080/appointment/view", this.user)
        .subscribe((data: any) => {
          data.forEach(element => {
            if (element.appointmentType === 'WALK_IN') {
              element.appointmentType = "Walk-in";
            }
            else if (element.appointmentType === 'ANNUAL_CHECKUP') {
              element.appointmentType = "Annual checkup"
            }
          });
          this.list = data;
        },
          error => { console.log(error); this.openSnackBar(error.error, "Close"); }
        );
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

  cancelBooking(appointment): void {
    const dialogRef = this.dialog.open(PatientCancelComponent, {
      width: '500px',
      data: appointment
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
