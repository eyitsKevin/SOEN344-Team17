import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-clinic-profile',
  templateUrl: './clinic-profile.component.html',
  styleUrls: ['./clinic-profile.component.css']
})
export class ClinicProfileComponent implements OnInit {

  constructor(private _activatedRoute: ActivatedRoute) {}
  id;

  ngOnInit() {
    this.id = this._activatedRoute.snapshot.params['id'];
  }

}
