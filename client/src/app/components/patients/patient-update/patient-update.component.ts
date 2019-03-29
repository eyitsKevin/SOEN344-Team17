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

    clinics = [];
    selectedClinic = null;

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
      this.getAllClinics();
    }


    addAppointmentToCalendar(appointment) {
      const title = appointment.startTime.match(/(T........)/)[0].slice(1, 6);
      const newEvent = {
        title: title,
        start: new Date(appointment.startTime),
        duration: 20,
        color: colors.red,
        data: appointment,
      };
      this.events.push(newEvent);
      this.refresh.next();
    }

    getAllClinics() {
      this.http.get("http://localhost:8080/clinics/view").subscribe(data => {
        this.clinics = [];
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
      if(this.selectedClinic == null) {
        this.selectedClinic = id;
      }
      this.events = [];
      if (this.data.appointmentType.includes('Walk-in')) {
        this.http
        .get('http://localhost:8080/clinics/availability/view/walkin/' + (this.viewDate.getMonth() + 1) + "/" + id)
        .subscribe((result: Array<Object>) => {
          result.map(availability => this.addAppointmentToCalendar(availability));
          result.forEach((element: any) => {
            if (element.appointmentType === 'WALK_IN') {
              element.appointmentType = 'Walk-in';
            }});
          });
        if (this.data.appointmentType.includes('Walk-in')) {
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

    openSnackBar(message: string, action: string) {
      this.snackBar.open(message, action, {
        duration: 5000,
      });
    }

    handleEvent(action: string, event): void {

      if(event.data.date === this.data.date && event.data.start === this.data.start) {
        this.openSnackBar('This is your current booked appointment', 'Close');
      } else {
      const dialogRef = this.dialog.open(PatientUpdateConfirmationComponent, {
        width: '500px',
        data: {'0': event.data, 'old': this.data}
      });

      dialogRef.afterClosed().subscribe(result => {
        this.dialogRef.close(result);
      });
    }
  }

  getAvailabilities() {
    this.getNewAvailabilities(this.selectedClinic);
}

}
