import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntitylineComponent } from './entityline.component';

describe('EntitylineComponent', () => {
  let component: EntitylineComponent;
  let fixture: ComponentFixture<EntitylineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntitylineComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntitylineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
