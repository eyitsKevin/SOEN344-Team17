import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PatientAuthenticationGuard implements CanActivate {

  constructor() { }
  
  canActivate(): boolean {
    return (localStorage.authentication==="patient");
  }
}
