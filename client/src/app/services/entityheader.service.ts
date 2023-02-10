import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EntityHeader} from "../common/entityheader";

//Сървисът имплементира коснумация на EntityHeader ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class EntityheaderService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/resource/entityheader/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/resource/entityheader/insert";
  deleteUrl:string = "http://localhost:4713/sharemenu/api/resource/entityheader/delete?id=";

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];

  constructor(private http : HttpClient) {}

  getAll(){
    return this.http.get<EntityHeader[]>(this.getAllUrl);
  }

  async save(entityheader : EntityHeader){
    return await this.http.post(this.saveUrl, entityheader, {responseType: 'text'}).toPromise();
  }
  delete(entityheader : EntityHeader){
    return this.http.delete(this.deleteUrl+ entityheader.id,{responseType : 'text'}).toPromise()
  }
}
