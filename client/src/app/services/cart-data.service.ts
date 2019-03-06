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
  var exists = false;
if(this.list.length == 0){
  this.list.push(appointment)
}
else{
for(var i=0; i<this.list.length; i++){
    if(this.list[i].id == appointment.id ){
      exists = true;
    }
    else {}
  }
  if(exists == false){
    this.list.push(appointment)
  }
}
}

getAllAppointments(){
  return this.list;
}

deleteAllAppointments(){
  return this.list = [];
}

removeAppointment(number){
  this.list.splice(number,1);
}
}
