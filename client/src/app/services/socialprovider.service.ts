import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Socialproviders} from "../common/socialprovider";

@Injectable({
  providedIn: 'root'
})
export class SocialproviderService {

  getALlUrl: string = "http://localhost:4713/sharemenu/api/socialNetworkProvider/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/socialNetworkProvider/insert";
  deleteUrl : string = "http://localhost:4713/sharemenu/api/socialNetworkProvider/delete?id=";

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];


  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Socialproviders[]>(this.getALlUrl);
  }

  async save(socialProvider : Socialproviders){
    return await this.http.post(this.saveUrl, socialProvider, {responseType: 'text'}).toPromise();
  }
  delete(socialnetworkprovider : Socialproviders){
    return this.http.delete(this.deleteUrl+ socialnetworkprovider.id,{responseType : 'text'}).toPromise()
  }
}
