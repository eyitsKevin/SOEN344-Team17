import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';

@Injectable({
  providedIn: 'root'
})

export class CartDataService {
  list = []
constructor() { }

addAppointment(appointment){
// if(this.list.includes(appointment.id)){

// }
// else this.list.push(appointment)
if(this.list.length == 0){
  this.list.push(appointment)
}
else
for(var i=0; i<this.list.length; i++){
    if(this.list[i].id == appointment.id ){
      
    }
    else this.list.push(appointment)
  }
}

getAllAppointments(){
  return this.list;
}

deleteAllAppointments(){
  return this.list = [];
}

removeAppointment(index){
  this.list.pop
}
}

