import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Patient} from '../models/patient';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  private messageSource = new BehaviorSubject<Patient>(null);
  patient = this.messageSource.asObservable();

  constructor() { }

  sendMessage(patient: Patient) {
    this.messageSource.next(patient);
  }
}
