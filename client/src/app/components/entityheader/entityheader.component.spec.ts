import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityheaderComponent } from './entityheader.component';

describe('EntityheaderComponent', () => {
  let component: EntityheaderComponent;
  let fixture: ComponentFixture<EntityheaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntityheaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntityheaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
