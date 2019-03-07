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
  selected: Patient;
  filteredOptions: Observable<Patient[]>;
  eventText;

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

  onSwipe(evt) {
    const x = Math.abs(evt.deltaX) > 40 ? (evt.deltaX > 0 ? 'right' : 'left') : '';
    const y = Math.abs(evt.deltaY) > 40 ? (evt.deltaY > 0 ? 'down' : 'up') : '';
    console.log('hello');
    this.eventText += `${x} ${y}<br/>`;
  }

}
