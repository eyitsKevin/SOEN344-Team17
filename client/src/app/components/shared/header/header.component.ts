import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.user.subscribe(user => this.user = user);
  }

}
