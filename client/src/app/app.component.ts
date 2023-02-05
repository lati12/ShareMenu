import { Component} from '@angular/core';
import {MenuItem, MessageService} from 'primeng/api';
import {StorageService} from "./services/storage.service";
import {AuthService} from "./services/auth.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  username?: string;
  items: MenuItem[] = [];

  constructor(private storageService: StorageService,  private messageService : MessageService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      this.router.navigate(['']).catch(console.error);
    }
    else
      this.router.navigate(['login']).catch(console.error);
  }



}
