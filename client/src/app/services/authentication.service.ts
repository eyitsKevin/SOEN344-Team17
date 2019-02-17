import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private authenticationSource = new BehaviorSubject<string>(null);
  authenticated = this.authenticationSource.asObservable();

  private userSource = new BehaviorSubject<Object>(null);
  user = this.userSource.asObservable();

  constructor() { }

  changeAuthentication(authenticated: string){
    this.authenticationSource.next(authenticated);
  }

  changeUser(user: Object){
    this.userSource.next(user);
    console.log(user);
  }
}
