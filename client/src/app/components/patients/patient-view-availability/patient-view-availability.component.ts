import {
  Component,
  ViewChild,
  TemplateRef,
  OnInit, Inject
} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {
  isSameMonth,
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView
} from 'angular-calendar';
import { PatientBookingComponent } from '../patient-booking/patient-booking.component';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {MAT_BOTTOM_SHEET_DATA} from '@angular/material';
import {NursePatientBookingComponent} from '../../nurses/nurse-patient-booking/nurse-patient-booking.component';
import {AuthenticationService} from '../../../services/authentication.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};

@Component({
  selector: 'app-patient-view-availability',
  templateUrl: 'patient-view-availability.component.html',
  styleUrls: ['patient-view-availability.component.scss']
})
export class PatientViewAvailabilityComponent implements OnInit{
  @ViewChild('modalContent') modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  };

  chooseClinic: FormGroup;
  refresh: Subject<any> = new Subject();
  clinics = [{id: "1", name: "clinic1"}, {id: "2", name: "clinic2"}, {id: "3", name: "clinic3"}, {id: "4", name: "clinic4"}];
  selectedClinic: {id: "1", name: "clinic1"};
  events: CalendarEvent[] = [];
  activeDayIsOpen = true;
  authenticated;

  constructor(private formBuilder: FormBuilder,
              public dialog: MatDialog,
              private router: Router,
              private http: HttpClient,
              @Inject(MAT_BOTTOM_SHEET_DATA) public data: any,
              private authenticationService: AuthenticationService) {}

  ngOnInit() {
    this.authenticationService.authenticated.subscribe(authenticated => this.authenticated = authenticated);
    this.getAllClinics();
    this.getNewAvailabilities(this.clinics[0]["id"]);
    this.chooseClinic = this.formBuilder.group({
      clinic: [[]]
    });
  }

  addAppointmentToCalendar(appointment) {
    const title = appointment.startTime.match(/(T........)/)[0].slice(1, 6);
    const newEvent = {
      title: title,
      start: new Date(appointment.startTime),
      duration: 20,
      color: colors.red,
      data: appointment
    };
    this.events.push(newEvent);
    this.refresh.next();
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      if (
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
    }
  }

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd
                    }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.handleEvent('Dropped or resized', event);
    this.refresh.next();
  }

  handleEvent(action: string, event): void {
    if (this.authenticated === 'patient') {
      const dialogRef = this.dialog.open(PatientBookingComponent, {
        width: '500px',
        height: '500px',
        data: event.data
      });

      dialogRef.afterClosed().subscribe(() => {
        console.log('The dialog was closed');
      });
    } else if (this.authenticated === 'nurse') {
      const nurseDialog = this.dialog.open(NursePatientBookingComponent, {
        width: '500px',
        height: '500px',
        data: {
          cart: event.data,
          patient: this.data.patient
        }
      });

      nurseDialog.afterClosed().subscribe(() => {
        console.log('The dialog was closed');
      });
    }}

    getAllClinics() {
      this.http.get("http://localhost:8080/clinics").subscribe(data => {
        for (var i in data) {
            let clinic = {
              id: data[i]["id"],
              name: data[i]["name"]
            };
          this.clinics.push(clinic);
        }
      });

    }

    getNewAvailabilities(id : string) {
      console.log(id);
      this.events = [];
      if (this.router.url.includes('walkin') || !this.data.booking) {
        this.http
        .get('http://localhost:8080/clinics/availability/view/walkin/' + (this.viewDate.getMonth() + 1) + "/" + id)
        .subscribe((result: Array<Object>) => {
          result.map(availability => this.addAppointmentToCalendar(availability));
          result.forEach((element: any) => {
            if (element.appointmentType === 'WALK_IN') {
              element.appointmentType = 'Walk-in';
            }});
          });
      } else if (this.router.url.includes('annual') || this.data.booking) {
        this.http
        .get('http://localhost:8080/clinics/availability/view/annual/'  + (this.viewDate.getMonth() + 1) + "/" + id)
        .subscribe((result: Array<Object>) => {
          result.map(availability => this.addAppointmentToCalendar(availability));
          result.forEach((element: any) => {
            if (element.appointmentType === 'ANNUAL_CHECKUP') {
              element.appointmentType = 'Annual Checkup';
            }});
        });
      }

      this.refresh.next();

    }

}
