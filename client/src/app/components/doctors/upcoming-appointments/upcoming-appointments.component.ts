import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-upcoming-appointments',
  templateUrl: './upcoming-appointments.component.html',
  styleUrls: ['./upcoming-appointments.component.scss']
})
export class UpcomingAppointmentsComponent implements OnInit {

  appointments = [];
  user;

  constructor(private authenticationService: AuthenticationService, private http: HttpClient) {}

  ngOnInit() {
    this.authenticationService.user.subscribe(user => this.user = user);
    this.getAppointments(this.user.permitNumber);
  }

  getAppointments(permit) {
  }

  convertTime(time) {
    let newTime = new Date(time);
    newTime.setHours(newTime.getHours() - 4);
    return newTime.toString();
  }

}
