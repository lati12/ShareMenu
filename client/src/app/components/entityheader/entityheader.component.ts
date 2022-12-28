import { Component, OnInit } from '@angular/core';
import {Entityheader} from "../../models/entityheader";
import {Template} from "../../models/template";
import {Users} from "../../models/users";
import {EntityheaderService} from "../../services/entityheader.service";
import {TemplateService} from "../../services/template.service";
import {UsersService} from "../../services/users.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-entityheader',
  templateUrl: './entityheader.component.html',
  styleUrls: ['./entityheader.component.scss']
})
export class EntityheaderComponent {
  entityHeaderDialog : boolean = false;

  public entityHeaders : Entityheader[] = [];

  public entityHeader : Entityheader = new  Entityheader();

  selectedEntityHeaders: Entityheader[]  = [];

  templates : Template [] = [];

  users: Users [] = [];

  submitted : boolean = false;
  constructor(private entityHeaderService : EntityheaderService,private templateService : TemplateService,private usersService : UsersService,  private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit(){
    this.entityHeaderService.getAll().subscribe(data => {
      this.entityHeaders = data;
    });
    this.templateService.getAll().subscribe(data => {
      this.templates = data
    });
    this.usersService.getAll().subscribe(data =>{
      this.users = data;
    });

  }

  openNew(){
    this.entityHeader= new Entityheader();
    this.submitted = false;
    this.entityHeaderDialog = true;
  }
  editEntityHeader(entityHeader : Entityheader) {
    this.entityHeader = {...entityHeader};
    this.entityHeaderDialog = true;
  }
  deleteEntityHeader(entityHeader : Entityheader){
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + entityHeader.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entityHeaders = this.entityHeaders.filter(val => val.id !== entityHeader.id);
        this.entityHeaderService.delete(entityHeader).then(() =>{
          this.entityHeader = new Entityheader();
        })
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }
  hideDialog(){
    this.submitted = false;
    this.entityHeaderDialog = false;
  }
  saveEntityHeader(){
    this.submitted = true;
    debugger
    this.entityHeaderService.save(this.entityHeader).then(() =>{
      this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
      if(!this.entityHeader.id){
        this.entityHeaders.push(this.entityHeader);
        this.entityHeaders = [...this.entityHeaders];
      }
      this.entityHeaderDialog = false;
      this.entityHeader = new Entityheader();
    });
  }
}
