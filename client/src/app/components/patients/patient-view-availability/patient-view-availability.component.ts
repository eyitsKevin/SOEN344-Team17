import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  OnInit, Inject
} from '@angular/core';
import {MatDialogModule, MatDialog} from '@angular/material/dialog';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours
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

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fa fa-fw fa-pencil"></i>',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      }
    }
  ];

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  activeDayIsOpen = true;
  authenticated;

  constructor(private modal: NgbModal, public dialog: MatDialog, private router: Router, private http: HttpClient, @Inject(MAT_BOTTOM_SHEET_DATA) public data: any, private authenticationService: AuthenticationService) {}

  ngOnInit() {
    this.authenticationService.authenticated.subscribe(authenticated => this.authenticated = authenticated);
    this.getNewAvailabilities();
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

  getNewAvailabilities() {
    if (this.router.url.includes('walkin') || !this.data.booking) {
      this.http
        .get('http://localhost:8080/availability/view/walkin/' + (this.viewDate.getMonth() + 1))
        .subscribe((result: Array<Object>) => {
          result.map(availability => this.addAppointmentToCalendar(availability));
        });
    } else if (this.router.url.includes('annual') || this.data.booking) {
      this.http
        .get('http://localhost:8080/availability/view/annual/'  + (this.viewDate.getMonth() + 1))
        .subscribe((result: Array<Object>) => {
          result.map(availability => this.addAppointmentToCalendar(availability));
        });
    }
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = true;
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
}
