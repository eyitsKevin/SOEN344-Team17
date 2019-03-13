import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthenticationService } from '../../../services/authentication.service';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { PatientCancelComponent } from '../patient-cancel/patient-cancel.component';
import { PatientUpdateComponent } from '../patient-update/patient-update.component';
import {UserDataService} from '../../../services/user-data.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private http: HttpClient,
    private userDataService: UserDataService) {}


    step = 0;
    pastList = [];
    futureList = [];
    user;
    authenticated;


  ngOnInit() {
    this.authenticationService.authenticated.subscribe(value => this.authenticated = value);
    this.getAppointments();
  }

  getAppointments() {
    if (this.authenticated === 'nurse') {
      this.userDataService.patient.subscribe(user => this.user = user);
    } else {
      this.authenticationService.user.subscribe(user => this.user = user);
    }

    this.http.post('http://localhost:8080/appointment/view', this.user)
        .subscribe((data: any) => {
          data.forEach(element => {
            if (element.appointmentType === 'WALK_IN') {
              element.appointmentType = 'Walk-in';
            } else if (element.appointmentType === 'ANNUAL_CHECKUP') {
              element.appointmentType = 'Annual checkup';
            }
            if (this.compareTime(element.date + 'T' + element.time)) {
              this.pastList.push(element);
            } else {
              this.futureList.push(element);
            }
          });
        },
          error => { console.log(error); this.openSnackBar(error.error, 'Close'); }
        );
  }

  compareTime(time) {
    let newTime = new Date(time);
    newTime.setHours(newTime.getHours() - 5);
    let currentTime = new Date();
    // true = appointment was in the past, false = appointment is in the future
    return currentTime > newTime;
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
      if (result) {
        this.futureList = this.futureList.filter(element =>
          element.id !== result
        );
      }
    });
  }

  updateBooking(appointment): void {
    const dialogRef = this.dialog.open(PatientUpdateComponent, {
      data: appointment
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.futureList = this.futureList.filter(element =>
          element.id !== result.appointmentId
        );
        const time = result.cart[0].startTime.split('T');
        if (result.cart[0].appointmentType === 'WALK_IN') {
          result.cart[0].appointmentType = 'Walk-in';
        } else if (result.cart[0].appointmentType === 'ANNUAL_CHECKUP') {
          result.cart[0].appointmentType = 'Annual checkup';
        }
        result.cart[0].time = time[1];
        result.cart[0].date = time[0];
        this.futureList.push(result.cart[0]);
      }
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
