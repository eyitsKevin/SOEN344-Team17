import {
    Component,
    ChangeDetectionStrategy,
    ViewChild,
    TemplateRef,
    OnInit,
    Inject
  } from '@angular/core';
import {
    isSameDay,
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
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MatSnackBar } from '@angular/material';
import { PatientUpdateConfirmationComponent } from '../patient-update-confirmation/patient-update-confirmation.component';

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
    selector: 'patient-update',
    templateUrl: 'patient-update.component.html',
    styleUrls: ['patient-update.component.scss']
})
export class PatientUpdateComponent implements OnInit {
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

    constructor(private modal: NgbModal, public dialog: MatDialog,
        public dialogRef: MatDialogRef<PatientUpdateComponent>,
        @Inject(MAT_DIALOG_DATA) public data,
        public snackBar: MatSnackBar,
        private http: HttpClient) {}

    ngOnInit() {
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
      if (this.data.appointmentType.includes('Walk-in')) {
        this.http
        .get('http://localhost:8080/availability/view/walkin/' + (this.viewDate.getMonth() + 1))
        .subscribe((result: Array<Object>) => {
          result.map(availability => this.addAppointmentToCalendar(availability));
        });
      } else if (this.data.appointmentType.includes('Annual checkup')) {
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
      const dialogRef = this.dialog.open(PatientUpdateConfirmationComponent, {
        width: '500px',
        height: '300px',
        data: {'new': event.data, 'old': this.data.id}
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    }
}
