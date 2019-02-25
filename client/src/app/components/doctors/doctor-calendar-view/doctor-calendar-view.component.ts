import { Component, ChangeDetectionStrategy, ViewChild, TemplateRef } from '@angular/core';
import { startOfHour, isSameDay, isSameMonth } from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView } from 'angular-calendar';

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

  events: CalendarEvent[] = [
    
  ];

  activeDayIsOpen: boolean = false;

  constructor(private modal: NgbModal) {}

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
      title: type[availabilityType].title, //call type - send
      start: startTime, //send
      duration: type[availabilityType].duration, //send
      end: null,
      color: type[availabilityType].color,
      draggable: true
    };
    this.handleEvent("add", newEvent);
    this.refresh.next();
  }

  apply(action, event) {
    if(action=="add") {
      this.events.push(event);
    }
    event.end = new Date(event.start.getTime() + ((event.duration)*60000));
    this.refresh.next();
  }

  remove(eventStart) {
    for(var i = 0; i < this.events.length; i++){
      if(this.events[i].start == eventStart){
        this.events.splice(i, 1); 
      }
    }
    this.refresh.next(); 
  }

}

