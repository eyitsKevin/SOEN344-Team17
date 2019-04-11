import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthenticationGuard implements CanActivate {

  constructor() { }
  
  canActivate(): boolean {
    return (localStorage.authentication==="admin");
  }
}
