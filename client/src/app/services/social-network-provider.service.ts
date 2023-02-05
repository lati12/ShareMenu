import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SocialNetworkProvider} from "../common/socialnetworkprovider";

@Injectable({
  providedIn: 'root'
})
export class SocialNetworkProviderService {

  getALlUrl: string = "http://localhost:4713/sharemenu/api/resource/socialNetworkProvider/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/resource/socialNetworkProvider/insert";
  deleteUrl : string = "http://localhost:4713/sharemenu/api/resource/socialNetworkProvider/delete?id=";

  status: string[] = ['OUTOFSTOCK', 'INSTOCK', 'LOWSTOCK'];


  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<SocialNetworkProvider[]>(this.getALlUrl);
  }

  async save(socialProvider : SocialNetworkProvider){
    return await this.http.post(this.saveUrl, socialProvider, {responseType: 'text'}).toPromise();
  }
  delete(socialnetworkprovider : SocialNetworkProvider){
    return this.http.delete(this.deleteUrl+ socialnetworkprovider.id,{responseType : 'text'}).toPromise()
  }
}
