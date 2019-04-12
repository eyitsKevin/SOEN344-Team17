import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageNurseComponent } from './manage-nurse.component';

describe('ManageNurseComponent', () => {
  let component: ManageNurseComponent;
  let fixture: ComponentFixture<ManageNurseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageNurseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageNurseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
