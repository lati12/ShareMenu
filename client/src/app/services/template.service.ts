import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Template} from "../common/template";
import {UserTemplate} from "../common/user-template";
import {UserTemplateComponent} from "../components/user-template/user-template.component";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на Template ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class  TemplateService {

  getAllUrl: string =environment.apiEndpoint + "/resource/template/get";
  saveUrl: string =environment.apiEndpoint + "/resource/template/insert";
  deleteUrl : string =environment.apiEndpoint + "/resource/template/delete?id=";

  getByUserUrl: string = environment.apiEndpoint + "/resource/template/getByUser";


  getUserTemplateUrl : string = environment.apiEndpoint +"/resource/template/getUserTemplate";
  deleteUserTemplateUrl : string = environment.apiEndpoint + "/resource/template/deleteUserTemplate?";
  insertUserTemplateUrl : string = environment.apiEndpoint +"/resource/template/insertUserTemplate";

  constructor(private http: HttpClient) {}


  getAll() {
    return this.http.get<Template[]>(this.getAllUrl);
  }

  getAllByUser() {
    return this.http.get<Template[]>(this.getByUserUrl);
  }

  async save(template: Template) {
    return await this.http.post(this.saveUrl, template, { responseType: 'text' }).toPromise();
  }
  delete(template : Template){
    return this.http.delete(this.deleteUrl+ template.id,{responseType : 'text'}).toPromise()
  }

  getUsersTemplates(){
    return this.http.get<UserTemplate[]>(this.getUserTemplateUrl);
  }

  deleteUserTemplate(userId: number, templateId: number){
    return this.http.delete(this.deleteUserTemplateUrl + "user_id=" + userId + "&template_id=" + templateId,{responseType : 'text'}).toPromise()
  }

  async saveUserTemplate(userTemplate: UserTemplate) {
    return await this.http.post(this.insertUserTemplateUrl, userTemplate, { responseType: 'text' }).toPromise();
  }
}
