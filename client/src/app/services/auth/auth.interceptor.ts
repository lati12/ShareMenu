import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HTTP_INTERCEPTORS,
  HttpErrorResponse, HttpClient
} from '@angular/common/http';
import {EMPTY, Observable, throwError} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";
import {AuthService} from "./auth.service";

const TOKEN_HEADER_KEY = 'Authorization';

//Имплементация на механизъм за всяка заявка да се добави "token" в header

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  public static AUTH_BEARER = 'Bearer ';

  private access_token: string | null;
  private refresh_token: string | null;

  constructor(
    private jwtHelper: JwtHelperService,
    private router: Router,
    private authService: AuthService,
    private http: HttpClient,
    private tokenStorageService: TokenStorageService
  ) {
    this.access_token = null;
    this.refresh_token = null;
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.access_token = this.tokenStorageService.getAccessToken(); //window.localStorage.getItem("access_token");

    if (request.headers.get('skip')) {
      return next.handle(request);
    }

    if (!this.authService.isTokenExpired(this.access_token)) {
      request = AuthInterceptor.setRequestHeader(request, this.access_token!);
      return next.handle(request);
    } else {
      this.router.navigate(['login']).catch(console.error); // both access and refresh token are expired
      return EMPTY;
    }
  }

  private static setRequestHeader(req: HttpRequest<any>, token: string) {
    req = req.clone({
      setHeaders: {
        Authorization: AuthInterceptor.AUTH_BEARER + token,
      },
    });
    return req;
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
