import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
//Имплементация на съхраняване/четене на "token" в сесията на браузера

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {


  logout() {
    window.localStorage.removeItem(TOKEN_KEY);
  }

  public saveAccessToken(token: string) {
    TokenStorageService.saveToken(TOKEN_KEY, token);
  }

  private static saveToken(key: string, value: string) {
    window.localStorage.removeItem(key);
    window.localStorage.setItem(key, value);
  }

  public getAccessToken(): string | null {
    return TokenStorageService.getToken(TOKEN_KEY);
  }


  private static getToken(key: string): string | null {
    return localStorage.getItem(key);
  }


  constructor() { }
}
