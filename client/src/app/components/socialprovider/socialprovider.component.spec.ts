import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialproviderComponent } from './socialprovider.component';

describe('SocialproviderComponent', () => {
  let component: SocialproviderComponent;
  let fixture: ComponentFixture<SocialproviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialproviderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SocialproviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
