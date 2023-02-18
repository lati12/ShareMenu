import {Component, isDevMode, OnInit} from '@angular/core';
import {EntityHeader} from "../../common/entityheader";
import {Sharemenu} from "../../common/sharemenu";
import {SocialNetworkConnectivity} from "../../common/socialNetworkConnectivity";
import {EntityheaderService} from "../../services/entityheader.service";
import {SocialNetworkConnectivityService} from "../../services/social-network-connectivity.service";
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

  socialproviders : SocialNetworkConnectivity [] = [];
  socialProvider : SocialNetworkConnectivity = new SocialNetworkConnectivity();

  displayInfoDialog: boolean = false;

  constructor(private sharemenuService: SharemenuService,private entityHeaderService:EntityheaderService, private messageService: MessageService,
              private  socialProviderService : SocialNetworkConnectivityService) { }

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
      this.messageService.add({severity:'success', summary: 'Успешно генериране на меню', life: 3000});
      console.log("done");
      saveAs(blocb, "menu.pdf");
    }).catch(arr => {
      console.log(arr);
    })
  }

  shareMenu(){
    this.sharemenuService.shareMenu(this.sharemenu).then(() =>{
      this.messageService.add({severity:'success', summary: 'Успешно споделено меню', life: 3000});
      console.log("done");
    }).catch(arr => {
      console.log(arr);
      this.messageService.add({severity:'error', summary: 'Неуспешно споделяне на меню', life: 3000});
    })
  }

  showInfoDialog() {
      this.displayInfoDialog = true;
  }
}
