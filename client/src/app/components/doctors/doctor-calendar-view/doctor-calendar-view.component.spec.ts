import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorCalendarViewComponent } from './doctor-calendar-view.component';

describe('DoctorCalendarViewComponent', () => {
  let component: DoctorCalendarViewComponent;
  let fixture: ComponentFixture<DoctorCalendarViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorCalendarViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorCalendarViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
