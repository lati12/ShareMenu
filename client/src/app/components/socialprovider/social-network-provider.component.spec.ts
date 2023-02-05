import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialNetworkProviderComponent } from './social-network-provider.component';

describe('SocialproviderComponent', () => {
  let component: SocialNetworkProviderComponent;
  let fixture: ComponentFixture<SocialNetworkProviderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialNetworkProviderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SocialNetworkProviderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
