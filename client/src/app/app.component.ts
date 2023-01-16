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

      items: [{
        label: 'Template',
        routerLink: '/template'
      },
        {
          label: 'Entity Header',
          routerLink: '/entityheader'
        },
        {
          label: 'Entity Lines',
          routerLink: '/entityline'
        },
        {
          label: 'Social Network Providers',
          routerLink: '/socialprovider'
        },
        {
          label: 'Users',
          routerLink: '/users'
        },
        {
          label: 'ItemCategory',
          routerLink: '/itemcategory'
        },
        {
          label: 'Item',
          routerLink: '/item'
        },
        {
          label: 'Entity Document',
          routerLink : '/entitydocument'
        }
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
