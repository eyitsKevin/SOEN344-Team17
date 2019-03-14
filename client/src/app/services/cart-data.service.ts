import { map } from 'rxjs/operators';
import { AuthenticationService } from './authentication.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})

export class CartDataService {
  list = [];
  user;
  constructor(private http: HttpClient, private userService: AuthenticationService) {
    this.userService.user.subscribe(user => this.user = user);
    this.http.post('/api/availability/cart/retrieve', this.user.healthCard )
    .subscribe( (data: Array<Object>)=> {
      data.map(element => this.list.push(element));
    });
  }
  init() {
    this.userService.user.subscribe(user => this.user = user);
    this.http.post('/api/availability/cart/retrieve', this.user.healthCard )
    .subscribe( (data: Array<Object>)=> {
      data.map(element => this.list.push(element));
    });
    // this.http.post('/api/availability/cart/retrieve', this.user.healthCard)
    // .pipe(map(data => this.list.push(data)));

  }


  addAppointment(appointment) {
    let exists = false;
      for (let i = 0; i < this.list.length; i++) {
        if (this.list[i].id === appointment.id ) {
          exists = true;
        }
    }
    if (!exists) {
      this.list.push(appointment);
      const patientAppointment = {
        patient: this.user,
        cart: this.list
      };
    this.list.filter(element =>  {if (element.appointmentType === 'Annual Checkup') {
      element.appointmentType = 'ANNUAL_CHECKUP';
    }
    if (element.appointmentType === 'Walk-in') {
      element.appointmentType = 'WALK_IN';
    }});
    this.http.post('/api/availability/cart/save', patientAppointment )
    .subscribe(() => {});
  }
  }

  getAllAppointments() {
    return this.list;
  }

  deleteAllAppointments() {
    this.list = [];
  }

  removeAppointment(number) {
    this.list.splice(number, 1);
  }
}
