import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  objectKeys = Object.keys;
  user;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.user.subscribe(user => this.user = user);
  }

}
