import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {firstValueFrom} from "rxjs";

// Компонентът служи за верифицарането на потребителски акаунт

const AUTH_API = 'http://localhost:4713/sharemenu/api/auth/';

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
