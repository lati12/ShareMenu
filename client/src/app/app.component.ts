import { Component} from '@angular/core';
import {MenuItem, MessageService} from 'primeng/api';
import {TokenStorageService} from "./services/auth/token-storage.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {AuthService} from "./services/auth/auth.service";
import {NotificationService} from "./services/notification.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isTokenExpired = false;
  username?: string;
  items: MenuItem[] = [];

  constructor(private location: Location, private tokenStorageService: TokenStorageService
    , private messageService : MessageService, private router: Router
    , private notificationService: NotificationService
    , private authService: AuthService) { }

  ngOnInit(): void {
    this.isTokenExpired = this.authService.isTokenExpired(this.tokenStorageService.getAccessToken());

    this.notificationService.notification$.subscribe((message) => {
      this.messageService.add({
        severity: message.severity,
        summary: message.summary,
        detail: message.detail,
      });
    });

    if (!this.isTokenExpired) {
      this.router.navigate(['']).catch(console.error);
      return;
    }
    let path = this.location.path();

    if (path.includes('/'))
      path = this.location.path().split('/')[1];

    if (path.includes('?'))
      path = path.split('?')[0];

    switch (path) {
      case 'confirm-email':
      case 'register':
        break;
      default:
        this.router.navigate(['login']).catch(console.error);
    }
  }
}
