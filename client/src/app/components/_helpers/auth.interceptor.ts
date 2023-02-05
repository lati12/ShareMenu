import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HTTP_INTERCEPTORS,
  HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {StorageService} from "../../services/storage.service";

const TOKEN_HEADER_KEY = 'x-access-token';
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  private isRefreshing = false;

  constructor(private storageService : StorageService) {
  }
  /*
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token =this.token.getUser();
    if (token != null){
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer' + token)})
    }
    return next.handle(req);
  }
   */

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    debugger
    req = req.clone({
      withCredentials: true,
    });
    debugger
    return next.handle(req)
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
];
