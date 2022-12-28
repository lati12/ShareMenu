import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SharemenuComponent } from './sharemenu.component';

describe('SharemenuComponent', () => {
  let component: SharemenuComponent;
  let fixture: ComponentFixture<SharemenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SharemenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SharemenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
