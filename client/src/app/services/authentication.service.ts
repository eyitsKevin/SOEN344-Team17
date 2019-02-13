import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private authenticationSource = new BehaviorSubject<string>(null);
  authenticated = this.authenticationSource.asObservable();

  constructor() { }

  changeAuthentication(authenticated: string){
    this.authenticationSource.next(authenticated);
  }
}
