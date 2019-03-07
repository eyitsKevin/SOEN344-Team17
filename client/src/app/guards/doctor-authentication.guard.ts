import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DoctorAuthenticationGuard implements CanActivate {

  constructor() { }
  
  canActivate(): boolean {
    return (localStorage.authentication==="doctor");
  }
}
