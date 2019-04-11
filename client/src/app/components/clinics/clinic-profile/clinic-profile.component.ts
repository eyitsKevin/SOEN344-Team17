import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {MatTreeNestedDataSource} from '@angular/material';
import {NestedTreeControl} from '@angular/cdk/tree';
import {DoctorDetails} from '../../../models/doctor-details';
import {NurseDetails} from '../../../models/nurse-details';
import {DoctorChildren} from '../../../models/doctor-children';
import {NurseChildren} from '../../../models/nurse-children';

@Component({
  selector: 'app-clinic-profile',
  templateUrl: './clinic-profile.component.html',
  styleUrls: ['./clinic-profile.component.css']
})

export class ClinicProfileComponent implements OnInit {

  constructor(private _activatedRoute: ActivatedRoute, private http: HttpClient) {}
  id;
  data;
  treeControl = new NestedTreeControl<DoctorDetails>(node => node.children);
  doctorDataSource = new MatTreeNestedDataSource<DoctorDetails>();
  nurseDataSource = new MatTreeNestedDataSource<NurseDetails>();

  doctorDetailsList: DoctorDetails[] = [];
  nurseDetailsList: NurseDetails[] = [];

  ngOnInit() {
    this.id = this._activatedRoute.snapshot.params['id'];
    this.getClinicById(this.id);
  }

  getClinicById(id) {
    this.http.get('/api/clinics/view/' + id).subscribe( data => {
      this.data = data;
      this.data.nurseDetails.forEach(nurse => {
        const nurseDetails = {} as NurseDetails;
        const nurseChildren = {} as NurseChildren;

        nurseDetails.firstName = nurse.firstName;
        nurseDetails.lastName = nurse.lastName;
        nurseDetails.children = [];

        nurseChildren.firstName = nurse.firstName;
        nurseChildren.lastName = nurse.lastName;
        nurseChildren.accessId = nurse.accessId;
        nurseChildren.clinicName = nurse.clinicName;

        nurseDetails.children.push(nurseChildren);
        this.nurseDetailsList.push(nurseDetails);
      });
      this.data.doctorDetailsList.forEach(doctor => {
        const doctorDetails = {} as DoctorDetails;
        const doctorChildren = {} as DoctorChildren;

        doctorDetails.firstName = doctor.firstName;
        doctorDetails.lastName = doctor.lastName;
        doctorDetails.children = [];
        doctorChildren.firstName = doctor.firstName;
        doctorChildren.lastName = doctor.lastName;
        doctorChildren.city = doctor.city;
        doctorChildren.clinicName = doctor.clinicName;
        doctorChildren.permitNumber = doctor.permitNumber;
        doctorChildren.specialty = doctor.specialty;

        doctorDetails.children.push(doctorChildren);
        this.doctorDetailsList.push(doctorDetails);
      });
      this.doctorDataSource.data = this.doctorDetailsList;
      this.nurseDataSource.data = this.nurseDetailsList;
    }
  );
  }

  hasChild(_: number, node: DoctorDetails) {
    console.log(node);
    return !!node.children && node.children.length > 0;
  }


}
