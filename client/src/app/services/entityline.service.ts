import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Entityline} from "../common/entityline";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на EntityLine ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class EntitylineService {

  getAllUrl: string = environment.apiEndpoint +"/resource/entityline/get?headerId=";
  saveUrl: string = environment.apiEndpoint +"/resource/entityline/insert";
  deleteUrl:string = environment.apiEndpoint +"/resource/entityline/delete?id=";

  constructor(private http: HttpClient) {}

  getAll(headerId: number){
    return this.http.get<Entityline[]>(this.getAllUrl + headerId);
  }

  async save(entityLine : Entityline){
    return await this.http.post(this.saveUrl, entityLine, {responseType: 'text'}).toPromise();
  }
  delete(entityLine : Entityline){
    return this.http.delete(this.deleteUrl+ entityLine.id,{responseType : 'text'}).toPromise()
  }

}
