import { Component, OnInit } from '@angular/core';
import {Item} from "../../models/item";
import {ItemCategory} from "../../models/item-category";
import {ItemService} from "../../services/item.service";
import {ItemCategoryService} from "../../services/item-category.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {

  itemDialog : boolean = false;

  public items : Item[] = [];

  public item : Item = new Item();

  public itemCategories : ItemCategory[] = [];

  submitted: boolean = false;
  constructor(private itemservice: ItemService,private itemCategoryService: ItemCategoryService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.itemservice.getAll().subscribe(data =>{
      this.items = data
    });
    this.itemCategoryService.getAll().subscribe(data =>{
      this.itemCategories = data
    });
  }

  openNew(){
    this.item = new Item();
    this.submitted = false;
    this.itemDialog = true;
  }
  editItem(item: Item){
    this.item = {...item};
    this.itemDialog = true;
  }
  deleteItem(item : Item){
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + item.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.items= this.items.filter(val => val.id !== item.id);
        this.itemservice.delete(item).then(() => {
          this.item = new Item();
        });
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }
  hideDialog(){
    this.submitted = false;
    this.itemDialog = true;
  }
  saveItem(){
    this.submitted = true;

    this.itemservice.save(this.item).then(() => {
      this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
      if (!this.item.id) {
        this.items.push(this.item);
        this.items = [...this.items];
      }
      this.itemDialog = false;
      this.item = new Item();
    });
  }

}
