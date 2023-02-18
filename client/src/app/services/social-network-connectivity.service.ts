import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SocialNetworkConnectivity} from "../common/socialNetworkConnectivity";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на SocialNetworkProvider ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class SocialNetworkConnectivityService {

  getALlUrl: string =environment.apiEndpoint + "/resource/socialNetworkConnectivity/get";
  saveUrl: string = environment.apiEndpoint + "/resource/socialNetworkConnectivity/insert";
  deleteUrl : string =environment.apiEndpoint + "/resource/socialNetworkConnectivity/delete?id=";

  getPagesUrl: string =environment.apiEndpoint + "/resource/socialNetworkConnectivity/findSocialNetworkPage";


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
