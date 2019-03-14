import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NursePatientBookingComponent } from './nurse-patient-booking.component';

describe('NursePatientBookingComponent', () => {
  let component: NursePatientBookingComponent;
  let fixture: ComponentFixture<NursePatientBookingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NursePatientBookingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NursePatientBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
