import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Template} from "../common/template";

@Injectable({
  providedIn: 'root'
})
export class TemplateService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/resource/template/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/resource/template/insert";
  deleteUrl : string = "http://localhost:4713/sharemenu/api/resource/template/delete?id=";

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];


  constructor(private http: HttpClient) {}


  getAll() {
    return this.http.get<Template[]>(this.getAllUrl);
  }

  async save(template: Template) {
    return await this.http.post(this.saveUrl, template, { responseType: 'text' }).toPromise();
  }
  delete(template : Template){
    return this.http.delete(this.deleteUrl+ template.id,{responseType : 'text'}).toPromise()
  }
}
