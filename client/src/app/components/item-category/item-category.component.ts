import { Component, OnInit } from '@angular/core';
import {ItemCategory} from "../../models/item-category";
import {ItemCategoryService} from "../../services/item-category.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-item-category',
  templateUrl: './item-category.component.html',
  styleUrls: ['./item-category.component.scss']
})
export class ItemCategoryComponent implements OnInit {

  itemcategoryDialog : boolean = false;

  public itemcategories : ItemCategory[] = [];

  public itemcategory : ItemCategory = new ItemCategory();

  selectedItemCategory : ItemCategory [] = [];

  submitted : boolean = false;
  constructor(private itemcategoryservice : ItemCategoryService, private messageService: MessageService, private confirmationService: ConfirmationService) { }


  ngOnInit(): void {
    this.itemcategoryservice.getAll().subscribe(data =>{
      this.itemcategories = data
    });
  }

  openNew(){
    this.itemcategory = new ItemCategory();
    this.submitted = false;
    this.itemcategoryDialog = true;
  }
  editItemCategory(itemcategory :ItemCategory){
    this.itemcategory = {...itemcategory};
    this.itemcategoryDialog = true;
  }
  deleteItemCategory(itemcategory : ItemCategory){
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + itemcategory.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.itemcategories = this.itemcategories.filter(val => val.id !== itemcategory.id);
        this.itemcategoryservice.delete(itemcategory).then(() =>{
          this.itemcategory = new ItemCategory();
        });
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }
  hideDialog(){
    this.submitted = false;
    this.itemcategoryDialog = false;
  }
  saveItemCategory() {
    this.submitted = true;

    this.itemcategoryservice.save(this.itemcategory).then(() => {
      this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
      if (!this.itemcategory.id) {
        this.itemcategories.push(this.itemcategory);
        this.itemcategories = [...this.itemcategories];
      }
      this.itemcategoryDialog = false;
      this.itemcategory = new ItemCategory();
    });
  }

}
