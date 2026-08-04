import { TestBed } from '@angular/core/testing';

import { DeadlineApi } from './deadline-api';

describe('DeadlineApi', () => {
  let service: DeadlineApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeadlineApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
