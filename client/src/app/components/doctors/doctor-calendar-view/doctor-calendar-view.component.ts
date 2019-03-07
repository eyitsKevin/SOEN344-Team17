import { Component, ChangeDetectionStrategy, ViewChild, TemplateRef } from '@angular/core';
import { isSameDay, isSameMonth } from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarEvent, CalendarEventTimesChangedEvent, CalendarView } from 'angular-calendar';
import { HttpClient } from "@angular/common/http";
import { AuthenticationService } from '../../../services/authentication.service';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};

const type: any = {
  walkin: {
    title: "Walk-In",
    color: colors.yellow,
    duration: 20
  },
  checkup: {
    title: "Annual Checkup",
    color: colors.red,
    duration: 60
  }
};

@Component({
  selector: 'app-doctor-calendar-view',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './doctor-calendar-view.component.html',
  styleUrls: ['./doctor-calendar-view.component.scss']
})
export class DoctorCalendarViewComponent {

  @ViewChild('modalContent') modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  modalData: {
    action: string;
    event: CalendarEvent;
  };
  refresh: Subject<any> = new Subject();
  events: CalendarEvent[] = [];
  user;

  activeDayIsOpen: boolean = false;

  constructor(private modal: NgbModal, private http: HttpClient, private authenticationService: AuthenticationService) {
    this.authenticationService.user.subscribe(user => this.user = user);
    this.getAvailabilities();
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      this.viewDate = date;
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
    }
  }

  eventTimesChanged({ event, newStart, newEnd }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.handleEvent('Dropped or resized', event);
    this.refresh.next();
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addAvailability(availabilityType): void {
    let startTime = new Date();
    startTime.setHours(8, 0, 0);
    let newEvent = {
      id: null,
      title: availabilityType,
      start: startTime,
      duration: type[availabilityType].duration,
      end: null,
      color: type[availabilityType].color,
      draggable: true
    };
    this.handleEvent("add", newEvent);
    this.refresh.next();
  }

  apply(action, event) {
    event.end = new Date(event.start.getTime() + ((event.duration) * 60000));
    if (action == "add") {
      this.postAvailability(event);
      this.events.push(event);
    }
    this.updateAvailability(event);
    this.refresh.next();
  }

  postAvailability(event) {
    var eventDTO = { doctorPermitNumber: this.user.permitNumber, id: event.id, start: new Date(event.start).toUTCString(), duration: event.duration, title: event.title, end: new Date(event.end).toUTCString() };
    console.log(eventDTO);
    this.http.post("http://localhost:8080/availability/create", eventDTO).subscribe();
  }

  updateAvailability(event) {

  }

  remove(eventStart) {
    for (var i = 0; i < this.events.length; i++) {
      if (this.events[i].start == eventStart) {
        this.events.splice(i, 1);
      }
    }
    this.refresh.next();
  }

  getAvailabilities() {
    this.http.get("http://localhost:8080/availability/doctor/" + this.user.permitNumber).subscribe(data => {
      for (var i in data) {
        let event = {
          id: data[i]["id"],
          title: data[i]["title"],
          start: new Date(data[i]["start"]),
          duration: data[i]["duration"],
          end: new Date(data[i]["end"]),
          color: type[data[i]["title"]].color,
          draggable: true
        };
        this.events.push(event);
      }
      this.refresh.next();
    });
  }

}

