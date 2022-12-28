import { TestBed } from '@angular/core/testing';

import { SharemenuService } from './sharemenu.service';

describe('SharemenuService', () => {
  let service: SharemenuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SharemenuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
