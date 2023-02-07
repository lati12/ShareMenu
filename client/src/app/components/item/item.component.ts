import { Component, OnInit } from '@angular/core';
import {Item} from "../../common/item";
import {ItemCategory} from "../../common/item-category";
import {ItemService} from "../../services/item.service";
import {ItemCategoryService} from "../../services/item-category.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {

  public items : Item[] = [];
  public item : Item = new Item();
  public itemCategories : ItemCategory[] = [];

  displayInfoDialog: boolean = false;
  dialog : boolean = false;
  submitted: boolean = false;

  constructor(private itemService: ItemService
              , private itemCategoryService: ItemCategoryService
              , private messageService: MessageService
              , private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.itemService.getAll().subscribe(data =>{
      this.items = data
    });
    this.itemCategoryService.getAll().subscribe(data =>{
      this.itemCategories = data
    });
  }

  openNewDialog(){
    this.item = new Item();
    this.submitted = false;
    this.dialog = true;
  }

  hideDialog(){
    this.submitted = false;
    this.dialog = true;
  }

  openEditDialog(item: Item){
    this.item = new Item();
    this.dialog = true;
  }

  showInfoDialog() {
    this.displayInfoDialog = true;
  }

  save(){
    this.submitted = true;

    this.itemService.save(this.item).then(() => {
      this.itemService.getAll().subscribe(data => {
        this.items = data;
        this.dialog = false;
        this.item = new Item();
        console.log("Done with save");
      });
    });
  }

  delete(item : Item) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете? ' + item.name + '?',
      header: 'Потвърждаване',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.itemService.delete(item).then(() => {
          this.item = new Item();
          console.log("Done with delete");
        });
      }
    });
  }

}
