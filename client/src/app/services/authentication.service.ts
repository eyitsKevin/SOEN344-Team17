import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private authenticationSource = new BehaviorSubject<boolean>(false);
  authenticated = this.authenticationSource.asObservable();

  constructor() { }

  changeAuthentication(authenticated: boolean){
    this.authenticationSource.next(authenticated);
  }
}
