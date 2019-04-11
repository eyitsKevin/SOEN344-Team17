import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-clinic',
  templateUrl: './clinic.component.html',
  styleUrls: ['./clinic.component.scss']
})
export class ClinicComponent implements OnInit {

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

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
