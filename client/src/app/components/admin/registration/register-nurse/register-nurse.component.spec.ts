import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterNurseComponent } from './register-nurse.component';

describe('RegisterNurseComponent', () => {
  let component: RegisterNurseComponent;
  let fixture: ComponentFixture<RegisterNurseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterNurseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterNurseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
