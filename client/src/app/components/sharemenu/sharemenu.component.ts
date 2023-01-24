import { Component, OnInit } from '@angular/core';
import {EntityHeader} from "../../common/entityheader";
import {Sharemenu} from "../../common/sharemenu";
import {Socialproviders} from "../../common/socialprovider";
import {EntityheaderService} from "../../services/entityheader.service";
import {SocialproviderService} from "../../services/socialprovider.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {SharemenuService} from "../../services/sharemenu.service";

@Component({
  selector: 'app-sharemenu',
  templateUrl: './sharemenu.component.html',
  styleUrls: ['./sharemenu.component.scss']
})
export class SharemenuComponent implements OnInit {

  entityheaders : EntityHeader[] = [];
  entityheader: EntityHeader = new EntityHeader();

  sharemenu : Sharemenu = new Sharemenu();

  shareMenus : Sharemenu [] = [];
  itemDialog: boolean = false;

  socialproviders : Socialproviders [] = [];
  socialProvider : Socialproviders = new Socialproviders();

  constructor(private sharemenuService: SharemenuService,private entityHeaderService:EntityheaderService, private  socialProviderService : SocialproviderService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.sharemenuService.getAll().subscribe(data =>{
      this.shareMenus = data
    });
    this.entityHeaderService.getAll().subscribe(data =>{
      this.entityheaders = data
    });
    this.socialProviderService.getAll().subscribe(data =>{
      this.socialproviders = data
    });
  }

}
