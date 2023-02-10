import {Component, isDevMode, OnInit} from '@angular/core';
import {EntityHeader} from "../../common/entityheader";
import {Sharemenu} from "../../common/sharemenu";
import {SocialNetworkProvider} from "../../common/socialnetworkprovider";
import {EntityheaderService} from "../../services/entityheader.service";
import {SocialNetworkProviderService} from "../../services/social-network-provider.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {SharemenuService} from "../../services/sharemenu.service";
// @ts-ignore
import { saveAs } from "file-saver";

// Компонентът ShareMenu служи за генерирването и споделянето на файл

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

  displayInfoDialog: boolean = false;

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
    this.sharemenuService.generateFile(this.sharemenu).then((blocb) =>{
      console.log("done");
      saveAs(blocb, "menu.pdf");
    }).catch(arr => {
      console.log(arr);
    })
  }

  shareMenu(){
    this.sharemenuService.shareMenu(this.sharemenu).then(() =>{
      console.log("done");
    }).catch(arr => {
      console.log(arr);
    })
  }

  showInfoDialog() {
      this.displayInfoDialog = true;
  }
}
