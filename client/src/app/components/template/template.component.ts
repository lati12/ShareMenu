import { Component, OnInit } from '@angular/core';
import { Template } from 'src/app/common/template';
import {TemplateService} from "../../services/template.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {FileUploadModule} from 'primeng/fileupload';
import {environment} from "../../../environments/environment";

// Компонентът Template служи за следните операции - добавяне и изтриване на файл

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss']
})
export class TemplateComponent implements OnInit {

  public url: string = environment.apiEndpoint + "/resource/upload-file/upload";
  submitted: boolean = false;
  dialog: boolean = false;

  public templates : Template[] = [];
  public template : Template = new Template();

  constructor(private templateService : TemplateService
              , private messageService: MessageService
              , private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.templateService.getAll().subscribe(data => {
      this.templates = data;
    });
  }
  openDialog(){
    this.template = new Template();
    this.submitted = false;
    this.dialog = true;
  }

  delete(template : Template) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете' + template.name + '?',
      header: 'Потвърждаване',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.templateService.delete(template).then(() => {
          this.templateService.getAll().subscribe(data => {
            this.templates = data;
            this.template = new Template();
            console.log("Done with delete");
          });
        })
      }
    });
  }

  showInfoDialog() {

  }
}
