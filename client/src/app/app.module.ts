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
import { HttpClientModule } from '@angular/common/http';


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
import { UploadFileComponent } from './upload-file/upload-file.component';
import { EntityheaderComponent } from './components/entityheader/entityheader.component';
import {EntitylineService} from "./services/entityline.service";
import {EntityheaderService} from "./services/entityheader.service";
import {ItemService} from "./services/item.service";
import {ItemCategoryService} from "./services/item-category.service";
import {SharemenuService} from "./services/sharemenu.service";
import {SocialNetworkProviderService} from "./services/social-network-provider.service";
import {TemplateService} from "./services/template.service";
import {UsersService} from "./services/users.service";
import {ItemComponent} from "./components/item/item.component";
import {ItemCategoryComponent} from "./components/item-category/item-category.component";
import {SharemenuComponent} from "./components/sharemenu/sharemenu.component";
import {SocialNetworkProviderComponent} from "./components/socialprovider/social-network-provider.component";
import {TemplateComponent} from "./components/template/template.component";
import {UsersComponent} from "./components/users/users.component";
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import {httpInterceptorProviders} from "./components/_helpers/auth.interceptor";
import {OverlayPanelModule} from "primeng/overlaypanel";

@NgModule({
  declarations: [
    AppComponent,
    UploadFileComponent,
    EntityheaderComponent,
    ItemComponent,
    ItemCategoryComponent,
    SharemenuComponent,
    SocialNetworkProviderComponent,
    TemplateComponent,
    UsersComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,

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
    ReactiveFormsModule

  ],
  providers: [MessageService,ConfirmationService, EntitylineService, EntityheaderService, ItemService,
    ItemCategoryService,SharemenuService,SocialNetworkProviderService,TemplateService,UsersService, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
