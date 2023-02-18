import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EntityHeader} from "../common/entityheader";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на EntityHeader ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class EntityheaderService {

  getAllUrl: string = environment.apiEndpoint + "/resource/entityheader/get";
  saveUrl: string = environment.apiEndpoint + "/resource/entityheader/insert";
  deleteUrl:string = environment.apiEndpoint + "/resource/entityheader/delete?id=";

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
