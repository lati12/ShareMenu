import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sharemenu} from "../common/sharemenu";

//Сървисът имплементира коснумация на ShareMenu ресурса от Spring boot сървъра;

@Injectable({
  providedIn: 'root'
})
export class SharemenuService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/resource/sharemenu/get";
  shareMenuUrl: string = "http://localhost:4713/sharemenu/api/resource/sharemenu/share-menu"

  generateFileUrl: string = "http://localhost:4713/sharemenu/api/resource/sharemenu/generate-file"
  constructor (private http : HttpClient) { }

  async generateFile(shareMenu : Sharemenu){
    return await this.http.post(this.generateFileUrl, shareMenu, {responseType : 'blob'}).toPromise();
  }

  async shareMenu(shareMenu : Sharemenu){
    return await this.http.post(this.shareMenuUrl, shareMenu, {responseType : 'text'}).toPromise();
  }
}
