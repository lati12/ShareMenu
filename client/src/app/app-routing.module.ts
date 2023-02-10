import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SocialNetworkProviderComponent} from "./components/socialprovider/social-network-provider.component";
import {TemplateComponent} from "./components/template/template.component";
import {UsersComponent} from "./components/users/users.component";
import {SharemenuComponent} from "./components/sharemenu/sharemenu.component";
import {ItemComponent} from "./components/item/item.component";
import {ItemCategoryComponent} from "./components/item-category/item-category.component";
import {EntityheaderComponent} from "./components/entityheader/entityheader.component";
import {HomeComponent} from "./components/home/home.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {Roles} from "./common/Roles";
import {AuthGuard} from "./services/auth/auth-guard.service";
import {ConfirmEmailComponent} from "./components/confirm-email/confirm-email.component";
import {BrowserModule} from "@angular/platform-browser";
import {UserTemplateComponent} from "./components/user-template/user-template.component";
import {AboutProjectComponent} from "./components/about-project/about-project.component";

//Тук се случва руутига между компонентите
const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'confirm-email', component: ConfirmEmailComponent},
  {
    path: '', component: HomeComponent,
    children: [
      {path: '', component: ProfileComponent, pathMatch: 'full'},
      {
        path: 'profile', component: ProfileComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'entityheader', component: EntityheaderComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'sharemenu', component: SharemenuComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'socialprovider', component: SocialNetworkProviderComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'template', component: TemplateComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN]
          }
      },
      {
        path: 'users', component: UsersComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN]
          }
      },
      {
        path: 'sharemenu', component: SharemenuComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'item', component: ItemComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'itemcategory', component: ItemCategoryComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'usertemplate', component: UserTemplateComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
      {
        path: 'about', component: AboutProjectComponent,
        canActivate: [AuthGuard],
        data:
          {
            roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER]
          }
      },
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
