import { Component, OnInit } from '@angular/core';
import {EntityHeader} from "../../common/entityheader";
import {Sharemenu} from "../../common/sharemenu";
import {SocialNetworkProvider} from "../../common/socialnetworkprovider";
import {EntityheaderService} from "../../services/entityheader.service";
import {SocialNetworkProviderService} from "../../services/social-network-provider.service";
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

  socialproviders : SocialNetworkProvider [] = [];
  socialProvider : SocialNetworkProvider = new SocialNetworkProvider();

  constructor(private sharemenuService: SharemenuService,private entityHeaderService:EntityheaderService,
              private  socialProviderService : SocialNetworkProviderService) { }

  ngOnInit(): void {
    this.entityHeaderService.getAll().subscribe(data =>{
      this.entityheaders = data;
    });

    this.socialProviderService.getAll().subscribe(data =>{
      this.socialproviders = data;
    });
  }

  generateFile(){
    this.sharemenuService.generateFile(this.sharemenu).then(() =>{
      console.log("done");
    }).catch(arr => {
      console.log(arr);
    })
  }

}
