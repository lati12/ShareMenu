import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {MenuItem} from "primeng/api";
import {StorageService} from "../../services/storage.service";
import {Users} from "../../common/users";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {Roles} from "../../common/Roles";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  items: MenuItem[] = [];
  isLoggedIn = false;
  roles: string[] = [];
  loggedUser: Users | undefined;


  slideDisplay: boolean = true;
  itemVisable: boolean = false;
  itemCategoryVisable: boolean = false;
  userVisable: boolean = false;
  entityHeaderVisable: boolean = false;
  shareMenuVisable: boolean = false;
  socialProviderVisable: boolean = false;
  templateVisable: boolean = false;


  constructor(private userService: UserService, private authService: AuthService, private storageService: StorageService, public router: Router) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
      this.loggedUser = this.storageService.getUser();

      this.setVisibleItems()
      let self = this;

      this.items = [{
        items: [
          {
            label: 'Entity Header',
            visible: this.entityHeaderVisable,
            command: (_event: Event) => {
              this.router.navigate(['entityheader']).catch(console.error)
            }
          },
          {
            label: 'Item',
            visible: this.itemVisable,
            command: (_event: Event) => {
              this.router.navigate(['item']).catch(console.error)
            }
          },
          {
            label: 'Item Category',
            visible: this.itemCategoryVisable,
            command: (_event: Event) => {
              this.router.navigate(['itemcategory']).catch(console.error)
            }
          },
          {
            label : 'Share Menu',
            visible: this.shareMenuVisable,
            command: (_event: Event) => {
              this.router.navigate(['sharemenu']).catch(console.error)
            }
          },
          {
            label: 'Template',
            visible: this.templateVisable,
            command: (_event: Event) => {
              this.router.navigate(['template']).catch(console.error)
            }
          },
          {
            label: 'Social Network Providers',
            visible: this.socialProviderVisable,
            command: (_event: Event) => {
              this.router.navigate(['socialprovider']).catch(console.error)
            }
          },
          {
            label: 'Users',
            visible: this.userVisable,
            command: (_event: Event) => {
              this.router.navigate(['users']).catch(console.error)
            }
          },
        ]
      }];
    }
  }

  setVisibleItems() {
    this.itemVisable = false;
    this.itemCategoryVisable = false;
    this.userVisable = false;
    this.shareMenuVisable = false;
    this.templateVisable = false;
    this.socialProviderVisable = false;
    if (this.roles == null || !this.isLoggedIn) {
      this.slideDisplay = false;
      return;
    }

    let self = this;
    this.roles.forEach(function (role) {
      if (role === Roles.ROLE_ADMIN) {
        self.itemVisable = true;
        self.itemCategoryVisable = true;
        self.userVisable = true;
        self.shareMenuVisable = true;
        self.templateVisable = true;
        self.socialProviderVisable = true;
      } else if (role === Roles.ROLE_USER) {
        self.itemVisable = true;
        self.itemCategoryVisable = true;
        self.shareMenuVisable = true;
        self.entityHeaderVisable = true;
      }
    });
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }

}
