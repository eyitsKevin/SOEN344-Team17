import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client';

  constructor(private http: HttpClient) {}

  get() {
    this.http
      .get('/api/controller/values',{responseType: 'text'})
      .subscribe(string => this.title = string.toString());
  }
}
