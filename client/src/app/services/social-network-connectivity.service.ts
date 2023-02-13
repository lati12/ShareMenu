import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SocialNetworkConnectivity} from "../common/socialNetworkConnectivity";

//Сървисът имплементира коснумация на SocialNetworkProvider ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class SocialNetworkConnectivityService {

  getALlUrl: string = "http://localhost:4713/sharemenu/api/resource/socialNetworkConnectivity/get";
  saveUrl: string = "http://localhost:4713/sharemenu/api/resource/socialNetworkConnectivity/insert";
  deleteUrl : string = "http://localhost:4713/sharemenu/api/resource/socialNetworkConnectivity/delete?id=";

  getPagesUrl: string = "http://localhost:4713/sharemenu/api/resource/socialNetworkConnectivity/findSocialNetworkPage";


  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<SocialNetworkConnectivity[]>(this.getALlUrl);
  }

  async getPage(socialNetworkConnectivity : SocialNetworkConnectivity) {
    return await this.http.post(this.getPagesUrl, socialNetworkConnectivity, {responseType: 'text'}).toPromise();
  }

  async save(socialProvider : SocialNetworkConnectivity){
    return await this.http.post(this.saveUrl, socialProvider, {responseType: 'text'}).toPromise();
  }
  delete(socialNetworkConnectivity : SocialNetworkConnectivity){
    return this.http.delete(this.deleteUrl+ socialNetworkConnectivity.id,{responseType : 'text'}).toPromise()
  }
}
