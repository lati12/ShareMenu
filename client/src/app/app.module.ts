import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { SplitButtonModule } from 'primeng/splitbutton';

import { MessageModule } from 'primeng/message';
import { TabMenuModule } from 'primeng/tabmenu';
import {MenuModule} from "primeng/menu";
import {SplitterModule} from "primeng/splitter";
import {ConfirmationService, MessageService} from "primeng/api";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from '@angular/forms';
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
import {EntityheaderComponent} from "./components/entityheader/entityheader.component";
import {EntitylineComponent} from "./components/entityline/entityline.component";
import {SocialproviderComponent} from "./components/socialprovider/socialprovider.component";
import {ItemComponent} from "./components/item/item.component";
import {ItemCategoryComponent} from "./components/item-category/item-category.component";
import {SharemenuComponent} from "./components/sharemenu/sharemenu.component";
import {Templateservice} from "./services/templateservice";
import {Usersservice} from "./services/usersservice";
import {Socialprovidersurvice} from "./services/socialprovidersurvice";
import {Itemservice} from "./services/itemservice";
import {Itemcategoryservice} from "./services/itemcategoryservice";
import {EntitylineService} from "./services/entitylineService";
import {Entityheaderservice} from "./services/entityheaderservice";
import {TemplateComponent} from "./components/template/template.component";
import {UsersComponent} from "./components/users/users.component";

@NgModule({
  declarations: [
    AppComponent,
    TemplateComponent,
    UsersComponent,
    EntityheaderComponent,
    EntitylineComponent,
    SocialproviderComponent,
    ItemComponent,
    ItemCategoryComponent,
    SharemenuComponent,
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
    DropdownModule

  ],
  providers: [MessageService,ConfirmationService,Templateservice,Usersservice,Socialprovidersurvice,
    Entityheaderservice,EntitylineService,Itemcategoryservice,Itemservice],
  bootstrap: [AppComponent]
})
export class AppModule { }
