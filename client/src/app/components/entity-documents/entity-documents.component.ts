import { Component, OnInit } from '@angular/core';
import {Entityline} from "../../models/entityline";
import {Entityheader} from "../../models/entityheader";
import {ConfirmationService, MessageService} from "primeng/api";
import {EntitylineService} from "../../services/entityline.service";

@Component({
  selector: 'app-entity-documents',
  templateUrl: './entity-documents.component.html',
  styleUrls: ['./entity-documents.component.scss']
})
export class EntityDocumentsComponent implements OnInit {
  EntityLineDialog: boolean = false;
  entityline: Entityline= new Entityline();
  submitted: boolean = false;
  entitylines: Entityline[] = [];
  selectedEntityLines: Entityline[] = [];

  constructor( private entityLineService : EntitylineService ,private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
  }

  openNew(){
    this.entityline = new Entityline();
    this.submitted = false;
    this.EntityLineDialog = true;
  }

  deleteEntityLine(entityline:Entityline) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + entityline.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entitylines = this.entitylines.filter(val => val.id !== entityline.id);
        this.entityLineService.delete(entityline).then(() =>{
          this.entityline = new Entityline();
        })
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }

  editEntityLine(entityline: Entityline) {
    this.entityline = {...entityline};
    this.EntityLineDialog = true;
  }

  hideDialog() {

  }

  saveEntityLine() {

  }
}
