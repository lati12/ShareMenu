import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { SplitButtonModule } from 'primeng/splitbutton';
import {DynamicDialogModule} from 'primeng/dynamicdialog';


import { MessageModule } from 'primeng/message';
import { TabMenuModule } from 'primeng/tabmenu';
import {MenuModule} from "primeng/menu";
import {SplitterModule} from "primeng/splitter";
import {ConfirmationService, MessageService} from "primeng/api";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';


import {InputNumberModule} from "primeng/inputnumber";
import {ToastModule} from "primeng/toast";
import {FileUploadModule} from "primeng/fileupload";
import {RippleModule} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {RatingModule} from "primeng/rating";
import {DialogModule} from "primeng/dialog";
import {RadioButtonModule} from "primeng/radiobutton";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {InputTextareaModule} from "primeng/inputtextarea";
import {DividerModule} from "primeng/divider";
import {DropdownModule} from 'primeng/dropdown';
import { EntityheaderComponent } from './components/entityheader/entityheader.component';
import {ItemComponent} from "./components/item/item.component";
import {ItemCategoryComponent} from "./components/item-category/item-category.component";
import {SharemenuComponent} from "./components/sharemenu/sharemenu.component";
import {SocialNetworkConnectivityComponent} from "./components/socialprovider/social-network-connectivity.component";
import {TemplateComponent} from "./components/template/template.component";
import {UsersComponent} from "./components/users/users.component";
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import {OverlayPanelModule} from "primeng/overlaypanel";
import {AuthInterceptor, authInterceptorProviders} from "./services/auth/auth.interceptor";
import {ConfirmEmailComponent} from "./components/confirm-email/confirm-email.component";
import { UserTemplateComponent } from './components/user-template/user-template.component';
import {ImageModule} from "primeng/image";
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {CheckboxModule} from "primeng/checkbox";
import {ProgressSpinnerModule} from "primeng/progressspinner";

@NgModule({
  declarations: [
    AppComponent,
    EntityheaderComponent,
    ItemComponent,
    ItemCategoryComponent,
    SharemenuComponent,
    SocialNetworkConnectivityComponent,
    TemplateComponent,
    UsersComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    ConfirmEmailComponent,
    UserTemplateComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ToolbarModule,
        ButtonModule,
        SplitButtonModule,
        TabMenuModule,
        MessageModule,
        MenuModule,
        BrowserAnimationsModule,
        SplitterModule,
        FormsModule,
        TableModule,
        HttpClientModule,
        InputNumberModule,
        ToastModule,
        FileUploadModule,
        RippleModule,
        InputTextModule,
        RatingModule,
        DialogModule,
        RadioButtonModule,
        ConfirmDialogModule,
        InputTextareaModule,
        DividerModule,
        DropdownModule,
        DynamicDialogModule,
        OverlayPanelModule,
        ReactiveFormsModule,
        ImageModule,
        CheckboxModule,
        ProgressSpinnerModule,
    ],
  providers: [authInterceptorProviders,
    MessageService,
    ConfirmationService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {provide: JWT_OPTIONS, useValue: JWT_OPTIONS},
    JwtHelperService],
  bootstrap: [AppComponent]
})
export class AppModule { }
