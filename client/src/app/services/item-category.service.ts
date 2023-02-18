import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ItemCategory} from "../common/item-category";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на ItemCategory ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class ItemCategoryService {

  getAllUrl: string = environment.apiEndpoint + "/resource/itemcategory/get";
  saveUrl: string = environment.apiEndpoint + "/resource/itemcategory/insert";
  deleteUrl:string = environment.apiEndpoint + "/resource/itemcategory/delete?id=";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<ItemCategory[]>(this.getAllUrl);
  }

  async save(itemcategory : ItemCategory){
    return await this.http.post(this.saveUrl, itemcategory, {responseType : 'text'}).toPromise();
  }
  delete(itemcategory : ItemCategory){
    return this.http.delete(this.deleteUrl+ itemcategory.id,{responseType : 'text'}).toPromise()
  }
}
