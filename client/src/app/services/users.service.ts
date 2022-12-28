import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Users} from "../models/users";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/users/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/users/insert";
  deleteUrl : string =  "http://localhost:4713/sharemenu/api/users/delete?id="

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Users[]>(this.getAllUrl);
  }

  async save(users : Users){
    return await this.http.post(this.saveUrl, users, {responseType: 'text'}).toPromise();
  }
  delete(users : Users){
    return this.http.delete(this.deleteUrl+ users.id,{responseType : 'text'}).toPromise()
  }
}
