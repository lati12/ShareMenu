import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialNetworkConnectivityComponent } from './social-network-connectivity.component';

describe('SocialproviderComponent', () => {
  let component: SocialNetworkConnectivityComponent;
  let fixture: ComponentFixture<SocialNetworkConnectivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialNetworkConnectivityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SocialNetworkConnectivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
