import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class PatientAuthenticationGuard implements CanActivate {
  
  authenticated;

  constructor(private authenticationService: AuthenticationService) { }
  
  canActivate(): boolean {
    this.authenticationService.authenticated.subscribe(authentication => this.authenticated = authentication);
    return (this.authenticated==="patient");
  }
}
