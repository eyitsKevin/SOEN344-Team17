import {
  Component,
  Input,
  OnInit,
} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Patient} from '../../../models/patient';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatBottomSheet} from '@angular/material';
import {PatientViewAvailabilityComponent} from '../../patients/patient-view-availability/patient-view-availability.component';

@Component({
  selector: 'app-nurse-booking',
  templateUrl: './nurse-booking.component.html',
  styleUrls: ['./nurse-booking.component.scss'],
  animations: [
    [
      trigger('simpleFadeAnimation', [
        state('in', style({opacity: 1})),
        transition(':enter', [
          style({opacity: 0}),
          animate(600 )
        ]),
        transition(':leave',
          animate(600, style({opacity: 0})))
      ])
    ]
  ]
})
export class NurseBookingComponent implements OnInit {

  myControl = new FormControl();
  options: Patient[];
  isDataLoaded;
  @Input() selected: Patient;
  filteredOptions: Observable<Patient[]>;
  eventText;
  bookingSelection = false;

  constructor(private http: HttpClient,
              private bottomSheet: MatBottomSheet) {
  }

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

  getSelection(patient: Patient): void {
      this.selected = patient;
  }

  displayFn(val: Patient) {
    return val ? val.healthCard : val;
  }

  onSwipe(evt) {
    const x = Math.abs(evt.deltaX) > 40 ? (evt.deltaX > 0 ? 'right' : 'left') : '';
    const y = Math.abs(evt.deltaY) > 40 ? (evt.deltaY > 0 ? 'down' : 'up') : '';
    this.eventText += `${x} ${y}<br/>`;
  }

}
