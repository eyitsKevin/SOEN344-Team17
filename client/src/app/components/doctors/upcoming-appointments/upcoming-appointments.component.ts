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

  constructor(private authenticationService: AuthenticationService, private http: HttpClient) { }

  ngOnInit() {
    this.authenticationService.user.subscribe(user => this.user = user);
    this.getAppointments(this.user.permitNumber);
  }

  getAppointments(permit) {
    this.http.get("http://localhost:8080/appointment/doctor/" + permit).subscribe(data => {
      for (var i in data) {
        let appointment = {
          date: data[i]["date"],
          time: data[i]["time"],
          appointmentType: data[i]["appointmentType"]=="WALK_IN" ? "Walk-In" : "Check up",
          patient: data[i]["patientDetails"].firstName + " " + data[i]["patientDetails"].lastName
        }
        this.appointments.push(appointment);
      }

      function comparebyDate(a, b) {
        if (a.date < b.date)
          return -1;
        if (a.date > b.date)
          return 1;
        return 0;
      }
  
      function comparebyTime(a, b) {
        if (a.time < b.time)
          return -1;
        if (a.time > b.time)
          return 1;
        return 0;
      }
  
      this.appointments.sort(comparebyTime);
      this.appointments.sort(comparebyDate);

    });

  }

}
