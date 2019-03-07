import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NurseAuthenticationGuard implements CanActivate {
  
  constructor() { }
  
  canActivate(): boolean {
    return (localStorage.authentication==="nurse");
  }
}
