import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CartDataService } from '../../../services/cart-data.service';

export interface DialogData {
    cart;
    payment;
}

@Component({
  selector: 'app-patient-payment',
  templateUrl: './patient-payment.component.html',
  styleUrls: ['./patient-payment.component.css'],
})
export class PatientPaymentComponent implements OnInit{

  payment: FormGroup;
  price;
  constructor( public dialogRef: MatDialogRef<PatientPaymentComponent>,
               @Inject(MAT_DIALOG_DATA) public data: DialogData,
               private formBuilder: FormBuilder,
               public snackBar: MatSnackBar,
               private http: HttpClient,
               private router: Router,
               private cartDataService: CartDataService) { }
  ngOnInit() {
    this.price = this.data.cart.length * 20;
    this.payment = this.formBuilder.group({
      ccNumber: ['', [Validators.required, Validators.pattern('[0-9]{16}')]],
      cvv: ['', [Validators.required, Validators.pattern('[0-9]{3}')]],
      exprMonth: ['', Validators.required],
      exprYear: [, Validators.required]
    });
  }

  onSubmit() {
    if (this.payment.valid) {
      const info = {
        price: this.price,
        data: this.data,
        ...this.payment.value
      };
      this.http.post('/api/availability/cart/checkout', info)
        .subscribe(data => {
            this.router.navigate(['patient']);
          },
        );
      this.data.cart = this.cartDataService.deleteAllAppointments();
      this.dialogRef.close();
      this.router.navigate(['patient']);
    }
  }

  cancel() {
    this.dialogRef.close();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 5000,
    });
  }

}
