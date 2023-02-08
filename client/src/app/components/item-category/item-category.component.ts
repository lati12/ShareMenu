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

  dialog : boolean = false;
  displayInfoDialog : boolean = false;

  public itemCategories : ItemCategory[] = [];
  public itemCategory : ItemCategory = new ItemCategory();

  submitted : boolean = false;
  constructor(private itemCategoryService : ItemCategoryService
              , private messageService: MessageService
              , private confirmationService: ConfirmationService) { }


  ngOnInit(): void {
    this.itemCategoryService.getAll().subscribe(data =>{
      this.itemCategories = data
    });
  }

  openNewDialog()  {
    this.itemCategory = new ItemCategory();
    this.submitted = false;
    this.dialog = true;
  }

  hideDialog(){
    this.submitted = false;
    this.dialog = false;
  }

  OpenEditDialog(itemCategory :ItemCategory){
    this.itemCategory = itemCategory;
    this.dialog = true;
  }

  showInfoDialog() {
    this.displayInfoDialog = true;
  }

  save() {
    this.submitted = true;

    this.itemCategoryService.save(this.itemCategory).then(() => {
      this.itemCategoryService.getAll().subscribe(data => {
        this.itemCategories = data;
        this.dialog = false;
        this.itemCategory = new ItemCategory();
        console.log("Done with save");
      });
    });
  }

  delete(itemcategory : ItemCategory){
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете? ' + itemcategory.name + '?',
      header: 'Потвърждаване',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.itemCategoryService.delete(itemcategory).then(() =>{
          this.itemCategoryService.getAll().subscribe(data => {
            this.itemCategories = data;
            this.itemCategory = new ItemCategory();
            console.log("Done with delete");
          })
        });
      }
    });
  }

}
