import { Component, OnInit } from '@angular/core';
import {Item} from "../../models/item";
import {Entityheader} from "../../models/entityheader";
import {EntitylineService} from "../../services/entityline.service";
import {EntityheaderService} from "../../services/entityheader.service";
import {ItemService} from "../../services/item.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {Entityline} from "../../models/entityline";

@Component({
  selector: 'app-entityline',
  templateUrl: './entityline.component.html',
  styleUrls: ['./entityline.component.scss']
})

export class EntitylineComponent {
  EntityLineDialog: boolean = false;

  public entitylines: Entityline[] = [];

  public entityline: Entityline = new Entityline();

  selectedEntitylines: Entityline[] = [];

  items : Item[] = [];

  entityheaders : Entityline[] = [];

  submitted: boolean = false;

  constructor(private entityLineService: EntitylineService,private entityHeaderService : EntitylineService,private itemService : ItemService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.entityLineService.getAll().subscribe(data => {
      this.entitylines = data
    });
    this.entityHeaderService.getAll().subscribe(data => {
      this.entityheaders = data
    });
    this.itemService.getAll().subscribe(data => {
      this.items = data
    });
  }

  openNew() {
    this.entityline = new Entityline();
    this.submitted = false;
    this.EntityLineDialog = true;
  }

  editEntityLine(entityline: Entityline) {
    this.entityline = {...entityline};
    this.EntityLineDialog = true;
  }

  deleteEntityLine(entityline: Entityline) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + entityline.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entitylines = this.entitylines.filter(val => val.id !== entityline.id)
        this.entityLineService.delete(entityline).then(() =>{
          this.entityline = new Entityline();
        });
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }

  hideDialog() {
    this.EntityLineDialog = false;
    this.submitted = false;
  }

  saveEntityLine() {
    this.submitted = true;
    this.entityLineService.save(this.entityline).then(() => {
      this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
      if (!this.entityline.id) {
        this.entitylines.push(this.entityline);
        this.entitylines = [...this.entitylines];
      }
      this.EntityLineDialog = false;
      this.entityline = new Entityline();
    });
  }
}


