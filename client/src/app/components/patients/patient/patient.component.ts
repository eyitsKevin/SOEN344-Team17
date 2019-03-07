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

    list = []
    user;
    healthcard;

  ngOnInit() {
    this.list = this.cartDataService.getAllAppointments();

    for(let i = 0; i<this.list.length; i++){
      const fullDate =  this.list[i].startTime.split("T");
      const fullDate2 =  this.list[i].endTime.split("T");
      this.list[i].day = fullDate[0];
      this.list[i].start = fullDate[1];
    }
  }

  getAppointments(){
    this.authenticationService.user.subscribe(user => this.user = user);
    this.healthcard = this.user.healthCard;
    const userWithList = {
      user: this.healthcard,
      list: this.list
    }
    this.http.post("http://localhost:8080/appointment/view", userWithList)
        .subscribe(data => {
          
        },
          error => { console.log(error); this.openSnackBar(error.error, "Close"); }
        );
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }
}
