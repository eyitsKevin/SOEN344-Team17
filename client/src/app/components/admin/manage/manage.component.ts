import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {MatDialog, MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.scss']
})
export class ManageComponent implements OnInit {

  constructor(private http: HttpClient,
              public snackBar: MatSnackBar) { }

  data;

  ngOnInit() {
    this.fetchAll();
  }

  fetchAll() {
    this.http.get('api/clinics/view/all').subscribe(data => {
      this.data = data;
    });
  }

  drop(event: CdkDragDrop<string[]>, clinic) {
    let doctor;
    let oldClinic;

    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {

      doctor = event.previousContainer.data[event.previousIndex];
      oldClinic  = doctor.clinicName;
      doctor.clinicName = clinic.name;
      doctor.clinicId = clinic.id;

      this.http.put('/api/admin/update/doctor', doctor )
        .subscribe(() => {
          transferArrayItem(
            event.previousContainer.data,
            event.container.data,
            event.previousIndex,
            event.currentIndex);
          this.openSnackBar(oldClinic + ' changed to ' + doctor.clinicName, 'Close');
        }, message => {
          this.openSnackBar(message.error.error, 'Close');
        });


    }


  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }
}
