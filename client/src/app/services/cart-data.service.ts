import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})

export class CartDataService {
  list = [];
  constructor() { 
    if(this.list.length==0){
      this.list = JSON.parse(localStorage.getItem("cart"));
    }
  }

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

    localStorage.setItem("cart", JSON.stringify(this.list));

  }

  getAllAppointments() {
    return this.list;
  }

  deleteAllAppointments() {
    this.list = [];
    localStorage.removeItem("cart");
  }

  removeAppointment(number) {
    this.list.splice(number, 1);
    localStorage.setItem("cart", JSON.stringify(this.list));
  }
}
