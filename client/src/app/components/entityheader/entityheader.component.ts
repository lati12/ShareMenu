import { Component, OnInit } from '@angular/core';
import {Template} from "../../common/template";
import {Users} from "../../common/users";
import {EntityheaderService} from "../../services/entityheader.service";
import {TemplateService} from "../../services/template.service";
import {UsersService} from "../../services/users.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {Entityline} from "../../common/entityline";
import {EntitylineService} from "../../services/entityline.service";
import {ItemService} from "../../services/item.service";
import {Item} from "../../common/item";
import {EntityHeader} from "../../common/entityheader";

// В компонентът е имплементиран процес, който си взаимодейства с потребителят за лесно и удобно създаване на "Меню"

@Component({
  selector: 'app-entityheader',
  templateUrl: './entityheader.component.html',
  styleUrls: ['./entityheader.component.scss']
})
export class EntityheaderComponent {
  entityHeaderDialog : boolean = false;
  entityLineDialog : boolean = false;
  entityLineDetailsDialog: boolean = false;
  displayInfoDialog : boolean = false;

  entityHeader : EntityHeader = new  EntityHeader();
  entityline: Entityline= new Entityline();

  entityHeaders : EntityHeader[] = [];
  entitylines: Entityline[] = [];
  templates : Template [] = [];
  items : Item [] = [];
  users: Users[] = [];
  submittedEntityHeader : boolean = false;
  submittedEntityLine : boolean = false;
  submittedEntityLineDetails : boolean = false;

  constructor(private entityHeaderService : EntityheaderService,
              private entityLineService: EntitylineService,
              private itemService : ItemService,
              private templateService : TemplateService,
              private usersService : UsersService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) { }

  ngOnInit(){
    this.entityHeaderService.getAll().subscribe(data => {
      this.entityHeaders = data;
    });

    this.templateService.getAllByUser().subscribe(data => {
      this.templates = data
    });
    this.usersService.getAll().subscribe(data =>{
      this.users = data;
    });

    this.itemService.getAll().subscribe(data => {
      this.items = data;
    })
  }

  openNewEntityHeader(){
    this.entityHeader= new EntityHeader();
    this.submittedEntityHeader = false;
    this.entityHeaderDialog = true;
  }

  openNewEntityLine(){
    this.submittedEntityLine = false;
    this.entityLineDialog = true;
  }

  openNewEntityLineDetails(){
    this.submittedEntityLineDetails = false;
    this.entityLineDetailsDialog = true;
  }

  hideEntityHeaderDialog(){
    this.submittedEntityHeader = false;
    this.entityHeaderDialog = false;
  }

  hideEntityLineDialog() {
    this.submittedEntityLine = false;
    this.entityLineDialog = false;
    this.entityHeader = new EntityHeader();
  }

  hideEntityLineDetailsDialog(){
    this.submittedEntityLineDetails = false;
    this.entityLineDetailsDialog = false;
  }

  saveEntityHeader(){
    this.submittedEntityHeader = true;
    debugger
    this.entityHeaderService.save(this.entityHeader).then(() =>{
      this.entityHeaderService.getAll().subscribe(data => {
        this.entityHeaders = data;
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Менюто е запазено', life: 3000});
        this.entityHeaderDialog = false;
        this.entityHeader = new EntityHeader();
      });
    });
  }

  saveEntityLineDetails() {
    this.submittedEntityLineDetails = true;
    this.entityline.entityHeader = this.entityHeader;
    this.entityLineService.save(this.entityline).then(() => {
      this.entityLineService.getAll(this.entityHeader.id).subscribe(data => {
        this.entitylines = data;
        this.entityLineDetailsDialog = false;
        this.entityline = new Entityline();
      });
    });
  }

  deleteEntityHeader(entityHeader: EntityHeader){
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете   ' + entityHeader.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entityHeaderService.delete(entityHeader).then(() => {
          this.entityHeaderService.getAll().subscribe(data => {
            this.entityHeaders = data;
            this.entityHeader = new EntityHeader();
            this.messageService.add({severity:'success', summary: 'Successful', detail: 'Header Deleted', life: 3000});
          });
        });
      }
    });
  }

  deleteEntityLineDetails(entityLine: Entityline){
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете линията? ' + entityLine.item.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entityLineService.delete(entityLine).then(() => {
          this.entityLineService.getAll(entityLine.entityHeader.id).subscribe(data => {
            this.entitylines = data;
            this.entityHeader = new EntityHeader();
            this.messageService.add({severity:'success', summary: 'Успешно изтриване', detail: 'Линята е изтрита', life: 3000});
          })
        });
      }
    });
  }

  editEntityHeader(entityHeader: EntityHeader) {
    this.entityHeader = entityHeader;
    this.entityHeaderDialog = true;
  }

  editEntityLineDetails(entityline: Entityline) {
    this.entityline = entityline;
    this.entityLineDetailsDialog = true;
  }

  onRowSelectEntityHeader(event: any) {
    this.entityLineService.getAll(event.data.id).subscribe(data => {
      this.entityHeader = event.data;
      this.entitylines = data;
      this.openNewEntityLine();
    })

  }

  showInfoDialog() {
    this.displayInfoDialog = true;
  }

  openNewDialog() {
    this.entityHeader = new EntityHeader();
    this.submittedEntityHeader = false;
    this.entityHeaderDialog = true;
  }
}
