import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Entityline} from "../models/entityline";

@Injectable({
  providedIn: 'root'
})
export class EntitylineService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/entityline/get";
  saveUrl: string = "http://localhost:4713/sharerest/api/entityline/insert";
  deleteUrl:string = "http://localhost:4713/sharerest/api/entityline/delete?id=";
  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];

  constructor(private http: HttpClient) {}

  getAll(){
    return this.http.get<Entityline[]>(this.getAllUrl);
  }

  async save(entityLine : Entityline){
    return await this.http.post(this.saveUrl, entityLine, {responseType: 'text'}).toPromise();
  }
  delete(entityLine : Entityline){
    return this.http.delete(this.deleteUrl+ entityLine.id,{responseType : 'text'}).toPromise()
  }

}
