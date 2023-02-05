import { Component, OnInit } from '@angular/core';
import { Template } from 'src/app/common/template';
import {TemplateService} from "../../services/template.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss']
})
export class TemplateComponent implements OnInit {

  templateDialog: boolean = false;

  public templates : Template[] = [];

  public template : Template = new Template();

  submitted: boolean = false;
  constructor(private templateService : TemplateService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.templateService.getAll().subscribe(data => {
      this.templates = data;
    });
  }
  openNew(){
    this.template = new Template();
    this.submitted = false;
    this.templateDialog = true;
  }

  deleteTemplate(template : Template) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + template.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.templates = this.templates.filter(val => val.id !== template.id);
        this.templateService.delete(template).then(() => {
          this.template = new Template();
        })
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }
}
