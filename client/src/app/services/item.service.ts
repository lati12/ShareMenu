import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Item} from "../common/item";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на Item ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  getAllUrl: string =environment.apiEndpoint + "/resource/item/get";
  saveUrl: string = environment.apiEndpoint +"/resource/item/insert";
  deleteUrl: string = environment.apiEndpoint +"/resource/item/delgetallete?id=";

  constructor(private http: HttpClient) {
  }

  getAll(){
    return this.http.get<Item[]>(this.getAllUrl);
  }

  async save(item:Item){
    return await this.http.post(this.saveUrl, item, {responseType : 'text'}).toPromise();
  }

  delete(item : Item){
    return this.http.delete(this.deleteUrl+ item.id,{responseType : 'text'}).toPromise()
  }
}
