import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {firstValueFrom} from "rxjs";
import {environment} from "../../../environments/environment";

// Компонентът служи за верифицарането на потребителски акаунт

const AUTH_API = environment.apiEndpoint + '/auth/';

@Injectable({
    providedIn: 'root',
})
export class ConfirmEmailService {
    verifcationUrl = AUTH_API + 'verify';

    constructor(private http: HttpClient) {}

    async doConfirmation(hash: string, confMsg: string) {
      return await firstValueFrom(this.http
        .post(this.verifcationUrl + '?hash=' + hash, '', { responseType: 'text', headers: { skip: 'true' } }));
    }
}
