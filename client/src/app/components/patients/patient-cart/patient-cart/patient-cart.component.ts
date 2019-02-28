import { Component, OnInit } from '@angular/core';
import { CartDataService } from '../../../../services/cart-data.service';
import { AuthenticationService } from '../../../../services/authentication.service';
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material";

@Component({
  selector: 'app-patient-cart',
  templateUrl: './patient-cart.component.html',
  styleUrls: ['./patient-cart.component.css']
})
export class PatientCartComponent implements OnInit {

  constructor(private cartDataService: CartDataService,
    private authenticationService: AuthenticationService,
    public snackBar: MatSnackBar,
    private http: HttpClient) { }
list = []
user;
healthcard;
  ngOnInit() {
    this.list = this.cartDataService.getAllAppointments();

    for(let i = 0; i<this.list.length; i++){
     const fullDate =  this.list[i].startTime.split("T");
     const fullDate2 =  this.list[i].endTime.split("T");
      this.list[i].day = fullDate[0];
      this.list[i].start = fullDate[1];
      this.list[i].end = fullDate2[1];
    }
  }

  checkout(){
    this.authenticationService.user.subscribe(user => this.user = user);
    this.healthcard = this.user.healthCard;
    const userWithList = {
      user: this.healthcard,
      list: this.list
    }
    this.http.post("http://localhost:8080/availability/cart/checkout", userWithList)
        .subscribe(data => {
          
        },
          error => { console.log(error); this.openSnackBar(error.error, "Close"); }
        );
    this.list = this.cartDataService.deleteAllAppointments();
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
