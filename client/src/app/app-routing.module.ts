import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SocialproviderComponent} from "./components/socialprovider/socialprovider.component";
import {TemplateComponent} from "./components/template/template.component";
import {UsersComponent} from "./components/users/users.component";
import {SharemenuComponent} from "./components/sharemenu/sharemenu.component";
import {ItemComponent} from "./components/item/item.component";
import {ItemCategoryComponent} from "./components/item-category/item-category.component";
import {EntityheaderComponent} from "./components/entityheader/entityheader.component";

const routes: Routes = [
  {path: 'entityheader', component:EntityheaderComponent},
  {path: 'sharemenu', component:SharemenuComponent},
  {path: 'socialprovider', component:SocialproviderComponent},
  {path: 'template', component: TemplateComponent},
  {path: 'users', component: UsersComponent},
  {path: 'sharemenu', component: SharemenuComponent},
  {path: 'item', component: ItemComponent},
  {path: 'itemcategory', component: ItemCategoryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
