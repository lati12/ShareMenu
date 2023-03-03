import { Injectable } from '@angular/core';
import {Subject} from "rxjs";
import {Message} from "primeng/api";

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  public notification$: Subject<Message> = new Subject();

  constructor() {}
}
