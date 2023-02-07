import { Component, OnInit } from '@angular/core';
import {ItemCategory} from "../../common/item-category";
import {ItemCategoryService} from "../../services/item-category.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-item-category',
  templateUrl: './item-category.component.html',
  styleUrls: ['./item-category.component.scss']
})
export class ItemCategoryComponent implements OnInit {

  itemcategoryDialog : boolean = false;
  displayInfoDialog : boolean = false;

  public itemCategories : ItemCategory[] = [];
  public itemCategory : ItemCategory = new ItemCategory();

  submitted : boolean = false;
  constructor(private itemCategoryService : ItemCategoryService, private messageService: MessageService, private confirmationService: ConfirmationService) { }


  ngOnInit(): void {
    this.itemCategoryService.getAll().subscribe(data =>{
      this.itemCategories = data
    });
  }

  openNew(){
    this.itemCategory = new ItemCategory();
    this.submitted = false;
    this.itemcategoryDialog = true;
  }
  editItemCategory(itemcategory :ItemCategory){
    this.itemCategory = {...itemcategory};
    this.itemcategoryDialog = true;
  }
  deleteItemCategory(itemcategory : ItemCategory){
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете? ' + itemcategory.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.itemCategories = this.itemCategories.filter(val => val.id !== itemcategory.id);
        this.itemCategoryService.delete(itemcategory).then(() =>{
          this.itemCategory = new ItemCategory();
        });
        this.messageService.add({severity:'success', summary: 'Успешно изтриване', detail: 'Категория е изтрита', life: 3000});
      }
    });
  }
  hideDialog(){
    this.submitted = false;
    this.itemcategoryDialog = false;
  }
  saveItemCategory() {
    this.submitted = true;

    this.itemCategoryService.save(this.itemCategory).then(() => {
      this.itemCategoryService.getAll().subscribe(data => {
        this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
        this.itemcategoryDialog = false;
        this.itemCategory = new ItemCategory();
      });
    });
  }

  showInfoDialog() {
    this.displayInfoDialog = true;

  }

  openNewDialog()  {
    this.itemCategory = new ItemCategory();
    this.submitted = false;
    this.itemcategoryDialog = true;
  }
}
