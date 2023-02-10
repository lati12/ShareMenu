import { Injectable } from '@angular/core';
import {Users} from "../../common/users";
import {observable} from "rxjs";
import {JwtResponse} from "../../common/jwt-response";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

//Имплементация на съхраняване/четене на "token" в сесията на браузера

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  logout(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: JwtResponse): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): Users{
    let obj = sessionStorage.getItem(USER_KEY);
    return JSON.parse(obj !== null ? obj : "");
  }

}
