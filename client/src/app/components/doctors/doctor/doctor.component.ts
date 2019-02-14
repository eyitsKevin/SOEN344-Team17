import { Component, OnInit } from '@angular/core';
import {Doctor} from '../DoctorModel';
@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})

export class DoctorComponent implements OnInit {

  doctor: Doctor = {
    name : "Tristan Vu",
    permit_number: "23451234",
    specialty: "Pediatrician",
    city : "Montreal",
    clinic : "CLSC"
  }
  constructor() { }

  ngOnInit() {
  }

}
