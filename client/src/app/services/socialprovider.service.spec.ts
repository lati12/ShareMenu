import { TestBed } from '@angular/core/testing';

import { SocialproviderService } from './socialprovider.service';

describe('SocialproviderService', () => {
  let service: SocialproviderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SocialproviderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
