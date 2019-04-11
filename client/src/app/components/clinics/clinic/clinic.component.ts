import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Patient} from "../../../models/patient";

@Component({
  selector: 'app-clinic',
  templateUrl: './clinic.component.html',
  styleUrls: ['./clinic.component.scss']
})
export class ClinicComponent implements OnInit {

  constructor(private http: HttpClient) { }

  clinics;

  ngOnInit() {
    this.getAllClinics();
  }

  getAllClinics() {
    this.http.get('api/clinics/view').subscribe(data => {
      this.clinics = data;
    });
  }

  // getClinicById(id) {
  //   this.http
  //     .get('/view/' + id, {responseType: 'text'})
  //     .subscribe(string => this.title = string.toString());
  // }

}
