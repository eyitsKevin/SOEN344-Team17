import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorViewAllComponent } from './doctor-view-all.component';

describe('DoctorViewAllComponent', () => {
  let component: DoctorViewAllComponent;
  let fixture: ComponentFixture<DoctorViewAllComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorViewAllComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorViewAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
