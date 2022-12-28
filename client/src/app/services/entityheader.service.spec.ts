import { TestBed } from '@angular/core/testing';

import { EntityheaderService } from './entityheader.service';

describe('EntityheaderService', () => {
  let service: EntityheaderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntityheaderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
