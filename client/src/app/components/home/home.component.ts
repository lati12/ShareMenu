import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Users} from "../../common/users";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {Roles} from "../../common/Roles";

// Компонентът Home служи визуализирането на главният панел

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
  userTemplateVisable : boolean = false;
  aboutVisible : boolean = false;


  constructor(private authService: AuthService, private tokenStorageService: TokenStorageService, public router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      this.loggedUser = this.tokenStorageService.getUser();

      this.setVisibleItems()
      let self = this;

      this.items = [{
        items: [
          {
            label: 'Меню',
            visible: this.entityHeaderVisable,
            command: (_event: Event) => {
              this.router.navigate(['entityheader']).catch(console.error)
            }
          },
          {
            label: 'Артикул',
            visible: this.itemVisable,
            command: (_event: Event) => {
              this.router.navigate(['item']).catch(console.error)
            }
          },
          {
            label: 'Категория на артикул',
            visible: this.itemCategoryVisable,
            command: (_event: Event) => {
              this.router.navigate(['itemcategory']).catch(console.error)
            }
          },
          {
            label : 'Споделяне на меню',
            visible: this.shareMenuVisable,
            command: (_event: Event) => {
              this.router.navigate(['sharemenu']).catch(console.error)
            }
          },
          {
            label: 'Темплейт',
            visible: this.templateVisable,
            command: (_event: Event) => {
              this.router.navigate(['template']).catch(console.error)
            }
          },
          {
            label: 'Социални мрежи',
            visible: this.socialProviderVisable,
            command: (_event: Event) => {
              this.router.navigate(['socialprovider']).catch(console.error)
            }
          },
          {
            label: 'Потребители',
            visible: this.userVisable,
            command: (_event: Event) => {
              this.router.navigate(['users']).catch(console.error)
            }
          },
          {
            label: 'Потребител - Темплейт',
            visible: this.userTemplateVisable,
            command: (_event: Event) => {
              this.router.navigate(['usertemplate']).catch(console.error)
            }
          },
          {
            label: 'Относно проекта',
            visible: this.aboutVisible,
            command: (_event: Event) => {
              this.router.navigate(['about']).catch(console.error)
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
    this.userTemplateVisable = false;
    this.aboutVisible = false;
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
        self.userTemplateVisable = true;
        self.aboutVisible = true;
      } else if (role === Roles.ROLE_USER) {
        self.itemVisable = true;
        self.itemCategoryVisable = true;
        self.shareMenuVisable = true;
        self.entityHeaderVisable = true;
        self.aboutVisible = true;
      }
    });
  }

  logout(): void {
    this.tokenStorageService.logout();
    window.location.reload();
  }
}
