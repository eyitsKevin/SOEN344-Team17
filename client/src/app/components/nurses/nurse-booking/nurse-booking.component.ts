import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Patient} from '../../../models/patient';
import {animate, group, state, style, transition, trigger} from '@angular/animations';
import {MatBottomSheet} from '@angular/material';
import {PatientViewAvailabilityComponent} from '../../patients/patient-view-availability/patient-view-availability.component';

@Component({
  selector: 'app-nurse-booking',
  templateUrl: './nurse-booking.component.html',
  styleUrls: ['./nurse-booking.component.scss'],
  animations: [
    trigger('slideInOut', [
      state('in', style({
        'max-height': '500px', 'opacity': '1', 'visibility': 'visible'
      })),
      state('out', style({
        'max-height': '0px', 'opacity': '0', 'visibility': 'hidden'
      })),
      transition('in => out', [group([
          animate('400ms ease-in-out', style({
            'opacity': '0'
          })),
          animate('600ms ease-in-out', style({
            'max-height': '0px'
          })),
          animate('700ms ease-in-out', style({
            'visibility': 'hidden'
          }))
        ]
      )]),
      transition('out => in', [group([
          animate('1ms ease-in-out', style({
            'visibility': 'visible'
          })),
          animate('600ms ease-in-out', style({
            'max-height': '500px'
          })),
          animate('800ms ease-in-out', style({
            'opacity': '1'
          }))
        ]
      )])
    ]),
  ]
})
export class NurseBookingComponent implements OnInit {
  constructor(private http: HttpClient,
              private bottomSheet: MatBottomSheet) {
  }

  myControl = new FormControl();
  options: Patient[];
  isDataLoaded;
  selected: Patient;
  filteredOptions: Observable<Patient[]>;
  eventText;
  currentState = 'in';
  bookingSelection = false;


  openBottomSheet(): void {
    this.bottomSheet.open(PatientViewAvailabilityComponent, {
      panelClass: 'mat-bottom-sheet-container-custom',
      data: { patient: this.selected, booking: this.bookingSelection}});
  }

  closeBottomSheet(): void {
    this.bottomSheet.dismiss();
  }


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
    this.changeState();
    this.selected = patient;
  }

  changeState() {
    this.currentState = this.currentState === 'in' ? 'out' : 'in';
  }

  onSwipe(evt) {
    const x = Math.abs(evt.deltaX) > 40 ? (evt.deltaX > 0 ? 'right' : 'left') : '';
    const y = Math.abs(evt.deltaY) > 40 ? (evt.deltaY > 0 ? 'down' : 'up') : '';
    console.log('hello');
    this.eventText += `${x} ${y}<br/>`;
  }

}
