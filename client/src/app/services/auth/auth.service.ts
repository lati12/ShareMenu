import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {firstValueFrom, Observable} from "rxjs";
import {Users} from "../../common/users";
import {LoginRequest} from "../../common/login-request";
import {RegisterRequest} from "../../common/register-request";
import {JwtResponse} from "../../common/jwt-response";

const AUTH_API = 'http://localhost:4713/sharemenu/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  async login(loginRequest: LoginRequest): Promise<JwtResponse> {
    return await firstValueFrom(this.http
      .post<JwtResponse>(AUTH_API + "login", loginRequest, httpOptions));
  }

  async register(user: RegisterRequest) {
    return await firstValueFrom(this.http
      .post(AUTH_API + "register", user, httpOptions));
  }
}
