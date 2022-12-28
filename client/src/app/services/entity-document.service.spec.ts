import { TestBed } from '@angular/core/testing';

import { EntityDocumentService } from './entity-document.service';

describe('EntityDocumentService', () => {
  let service: EntityDocumentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EntityDocumentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
