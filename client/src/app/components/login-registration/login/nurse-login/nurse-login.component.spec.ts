import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NurseLoginComponent } from './nurse-login.component';

describe('NurseLoginComponent', () => {
  let component: NurseLoginComponent;
  let fixture: ComponentFixture<NurseLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NurseLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NurseLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
