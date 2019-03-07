/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { CartDataService } from './cart-data.service';

describe('Service: CartData', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CartDataService]
    });
  });

  it('should ...', inject([CartDataService], (service: CartDataService) => {
    expect(service).toBeTruthy();
  }));
});
