import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})

export class CartDataService {
  list = [];
  constructor() { }

  addAppointment(appointment) {
    let exists = false;
    if (this.list.length === 0) {
      this.list.push(appointment);
    } else {
      for (let i = 0; i < this.list.length; i++) {
        if (this.list[i].id === appointment.id ) {
          exists = true;
        }
      }
      if (exists === false) {
        this.list.push(appointment);
      }
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
