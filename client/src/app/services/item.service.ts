import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Item} from "../common/item";

//Сървисът имплементира коснумация на Item ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/resource/item/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/resource/item/insert";
  deleteUrl: string = "http://localhost:4713/sharemenu/api/resource/item/delete?id=";

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
