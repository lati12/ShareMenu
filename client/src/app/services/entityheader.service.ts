import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Entityheader} from "../models/entityheader";

@Injectable({
  providedIn: 'root'
})
export class EntityheaderService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/entityheader/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/entityheader/insert";
  deleteUrl:string = "http://localhost:4713/sharemenu/api/entityheader/delete?id=";

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];

  constructor(private http : HttpClient) {}

  getAll(){
    return this.http.get<Entityheader[]>(this.getAllUrl);
  }

  async save(entityheader : Entityheader){
    return await this.http.post(this.saveUrl, entityheader, {responseType: 'text'}).toPromise();
  }
  delete(entityheader : Entityheader){
    return this.http.delete(this.deleteUrl+ entityheader.id,{responseType : 'text'}).toPromise()
  }
}
