import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sharemenu} from "../common/sharemenu";

@Injectable({
  providedIn: 'root'
})
export class SharemenuService {

  getAllUrl: string = "http://localhost:4713/sharemenu/api/sharemenu/get";


  constructor (private http : HttpClient) { }

  getAll(){
    return this.http.get<Sharemenu[]>(this.getAllUrl);
  }
}
