import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ItemCategory} from "../models/item-category";

@Injectable({
  providedIn: 'root'
})
export class ItemCategoryService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/itemcategory/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/itemcategory/insert";
  deleteUrl:string = "http://localhost:4713/sharemenu/api/itemcategory/delete?id=";

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];

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
