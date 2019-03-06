import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Patient} from '../../../models/patient';

@Component({
  selector: 'app-nurse-booking',
  templateUrl: './nurse-booking.component.html',
  styleUrls: ['./nurse-booking.component.scss']
})
export class NurseBookingComponent implements OnInit {

  constructor(private http: HttpClient) {
  }

  myControl = new FormControl();
  options: Patient[];
  isDataLoaded;
  selected;
  filteredOptions: Observable<Patient[]>;

  ngOnInit() {
    this.fetchAllPatient().subscribe( x => {
        this.options = x;
        this.isDataLoaded = true;
        this.filteredOptions = this.myControl.valueChanges
          .pipe(
            startWith(''),
            map(patient => patient ? this._filter(patient) : this.options.slice())
          );
      }
    );
  }

  fetchAllPatient(): Observable<Patient[]> {
    return this.http.get<Patient[]>('api/nurses/patients/');
  }

  private _filter(value: any): Patient[] {
    if (value !== undefined) {
      const filterValue = value.toLowerCase();
      return this.options.filter(option => option.healthCard.toLowerCase().indexOf(filterValue) === 0);
    }
  }

  displayFn(patient: Patient): any {
    this.selected = patient;
    return patient ? patient.healthCard : patient;
  }

  getSelection(patient: Patient): void {
    this.selected = patient;
  }

}
