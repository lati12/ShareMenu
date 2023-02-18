import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sharemenu} from "../common/sharemenu";
import {environment} from "../../environments/environment";

//Сървисът имплементира коснумация на ShareMenu ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class SharemenuService {

  getAllUrl: string = environment.apiEndpoint + "/resource/sharemenu/get";
  shareMenuUrl: string = environment.apiEndpoint + "/resource/sharemenu/share-menu"

  generateFileUrl: string = environment.apiEndpoint + "/resource/sharemenu/generate-file"
  constructor (private http : HttpClient) { }

  async generateFile(shareMenu : Sharemenu){
    return await this.http.post(this.generateFileUrl, shareMenu, {responseType : 'blob'}).toPromise();
  }

  async shareMenu(shareMenu : Sharemenu){
    return await this.http.post(this.shareMenuUrl, shareMenu, {responseType : 'text'}).toPromise();
  }
}
