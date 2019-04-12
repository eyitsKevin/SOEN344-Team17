import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-manage-nurse',
  templateUrl: './manage-nurse.component.html',
  styleUrls: ['./manage-nurse.component.scss']
})
export class ManageNurseComponent implements OnInit {

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
    let nurse;
    let oldClinic;

    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {

      nurse = event.previousContainer.data[event.previousIndex];
      oldClinic  = nurse.clinicName;
      nurse.clinicName = clinic.name;
      nurse.clinicId = clinic.id;

      this.http.put('/api/admin/update/nurse', nurse )
        .subscribe(() => {
          transferArrayItem(
            event.previousContainer.data,
            event.container.data,
            event.previousIndex,
            event.currentIndex);
          this.openSnackBar(oldClinic + ' changed to ' + nurse.clinicName, 'Close');
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
