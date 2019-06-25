import { TestBed, inject } from '@angular/core/testing';

import { InvestoAvenueService } from './investo-avenue.service';

describe('InvestoAvenueService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InvestoAvenueService]
    });
  });

  it('should be created', inject([InvestoAvenueService], (service: InvestoAvenueService) => {
    expect(service).toBeTruthy();
  }));
});
