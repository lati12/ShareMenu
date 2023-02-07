import { Component} from '@angular/core';
import {MenuItem, MessageService} from 'primeng/api';
import {TokenStorageService} from "./services/auth/token-storage.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isLoggedIn = false;
  username?: string;
  items: MenuItem[] = [];

  constructor(private location: Location, private tokenStorageService: TokenStorageService, private messageService : MessageService, private router: Router) { }

  ngOnInit(): void {
    debugger
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
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
