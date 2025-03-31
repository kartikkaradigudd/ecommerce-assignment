import { TestBed } from '@angular/core/testing';

import { SalesStatsApiService } from './sales-stats-api.service';

describe('SalesStatsApiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SalesStatsApiService = TestBed.get(SalesStatsApiService);
    expect(service).toBeTruthy();
  });
});
