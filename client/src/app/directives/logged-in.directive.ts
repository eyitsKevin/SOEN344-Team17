import { Directive } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Directive({
  selector: '[appLoggedIn]'
})
export class LoggedInDirective {

  constructor(private authenticationService: AuthenticationService, private router: Router) { 
    
    if(localStorage.authentication==null){
      this.router.navigate(['login']);
    }
    else{
      this.authenticationService.changeAuthentication(localStorage.authentication);
      this.authenticationService.changeUser(JSON.parse(localStorage.getItem('user')));
    }
  }

}
