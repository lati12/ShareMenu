import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sharemenu} from "../common/sharemenu";

@Injectable({
  providedIn: 'root'
})
export class SharemenuService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/resource/sharemenu/get";

  generateFileUrl: string = "http://localhost:4713/sharemenu/api/resource/sharemenu/generate-file"
  constructor (private http : HttpClient) { }

  async generateFile(shareMenu : Sharemenu){
    return await this.http.post(this.generateFileUrl, shareMenu, {responseType : 'blob'}).toPromise();
  }
}
