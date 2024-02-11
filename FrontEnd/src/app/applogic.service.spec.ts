import { TestBed } from '@angular/core/testing';

import { ApplogicService } from './applogic.service';

describe('ApplogicService', () => {
  let service: ApplogicService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApplogicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
