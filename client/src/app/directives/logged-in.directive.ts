import { Directive } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Directive({
  selector: '[appLoggedIn]'
})
export class LoggedInDirective {

  authenticated;

  constructor(private authenticationService: AuthenticationService, private router: Router) { 

    if(localStorage.authentication!=null){
      this.authenticationService.changeAuthentication(localStorage.authentication);
      this.authenticationService.changeUser(JSON.parse(localStorage.getItem('user')));
    }

    this.authenticationService.authenticated.subscribe(authentication => this.authenticated = authentication);
    
    if(this.authenticated==null){
      this.router.navigate(['login']);
    }
  }

}
