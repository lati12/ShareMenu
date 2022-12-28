import { TestBed } from '@angular/core/testing';

import { EntitylineService } from './entityline.service';

describe('EntitylineService', () => {
  let service: EntitylineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntitylineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
