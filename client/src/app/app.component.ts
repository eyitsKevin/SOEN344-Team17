import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {animate, query, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [
    trigger('myAnimation', [
      transition('* => *', [
        query(
          ':enter',
          [style({ opacity: 0 })],
          { optional: true }
        ),
        query(
          ':enter',
          [style({ opacity: 0 }), animate('0.5s', style({ opacity: 1 }))],
          { optional: true }
        )
      ])
    ]),
  ]
})
export class AppComponent {
  title = 'client';

  constructor(private http: HttpClient) {}

  get() {
    this.http
      .get('/api/controller/values', {responseType: 'text'})
      .subscribe(string => this.title = string.toString());
  }
}
