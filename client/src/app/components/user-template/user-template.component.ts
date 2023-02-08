import { Component, OnInit } from '@angular/core';
import {Template} from "../../common/template";
import {UserTemplate} from "../../common/user-template";
import {Users} from "../../common/users";
import {UsersService} from "../../services/users.service";
import {TemplateService} from "../../services/template.service";
import {Item} from "../../common/item";
import {ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-user-template',
  templateUrl: './user-template.component.html',
  styleUrls: ['./user-template.component.scss']
})
export class UserTemplateComponent implements OnInit {

  templates: Template[] = [];
  users: Users[] = [];
  userTemplate: UserTemplate = new UserTemplate();
  userTemplates: UserTemplate[] = [];

  constructor(private userService: UsersService, private templateService: TemplateService, private confirmationService: ConfirmationService) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe(data => {
      this.users = data;
    })

    this.templateService.getAll().subscribe(data => {
      this.templates = data;
    })

    this.templateService.getUsersTemplates().subscribe(data => {
      this.userTemplates = data;
    })
  }

  showInfoDialog() {

  }

  generateMap() {
    this.templateService.saveUserTemplate(this.userTemplate).then(() => {
      this.templateService.getUsersTemplates().subscribe(data => {
        this.userTemplates = data;
      })
    })
  }

  delete(userTemplate: UserTemplate) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете записа?',
      header: 'Потвърждаване',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.templateService.deleteUserTemplate(userTemplate.user.id, userTemplate.template.id).then(() => {
          this.templateService.getUsersTemplates().subscribe(data => {
            this.userTemplates = data;
            console.log("Done with delete");
          });
        });
      }
    });
  }
}
