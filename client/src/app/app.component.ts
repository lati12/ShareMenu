import { Component} from '@angular/core';
import {MenuItem, MessageService} from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  items: MenuItem[] = [];

  constructor(private messageService: MessageService) {}

  ngOnInit() {
    this.items = [{
      items: [
        {
          label: 'Entity Header',
          routerLink: '/entityheader'
        },
        {
          label: 'Item',
          routerLink: '/item'
        },
        {
          label: 'Item Category',
          routerLink: '/itemcategory'
        },
        {
          label : 'Share Menu',
          routerLink : '/sharemenu'
        },
        {
          label: 'Template',
          routerLink: '/template'
        },
        {
          label: 'Social Network Providers',
          routerLink: '/socialprovider'
        },
        {
          label: 'Users',
          routerLink: '/users'
        },
      ]
    }];
  }

  update() {
    this.messageService.add({severity:'success', summary:'Success', detail:'Data Updated'});
  }

  delete() {
    this.messageService.add({severity:'warn', summary:'Delete', detail:'Data Deleted'});
  }

}
