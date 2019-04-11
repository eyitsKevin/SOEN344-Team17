import {DoctorChildren} from './doctor-children';

export interface DoctorDetails {
  firstName: string;
  lastName: string;
  children?: DoctorChildren[];
}
