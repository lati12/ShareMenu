import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityDocumentsComponent } from './entity-documents.component';

describe('EntityDocumentsComponent', () => {
  let component: EntityDocumentsComponent;
  let fixture: ComponentFixture<EntityDocumentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntityDocumentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntityDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
