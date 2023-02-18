import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Users} from "../common/users";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на Users ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  getAllUrl: string = environment.apiEndpoint + "/resource/users/get";
  saveUrl: string = environment.apiEndpoint + "/resource/users/insert";
  deleteUrl : string = environment.apiEndpoint + "/resource/users/delete?id="


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
