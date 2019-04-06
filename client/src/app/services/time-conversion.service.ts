import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class TimeConversion {

    constructor() { }

    UTCtoLocal(timeUTC) {
        return new Date(timeUTC + ":00 UTC");
    }

    LocaltoUTC(timeLocal) {
        return new Date(timeLocal).toUTCString();
    }

}