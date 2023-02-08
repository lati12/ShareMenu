import { Component, OnInit } from '@angular/core';
import {SocialNetworkProvider} from "../../common/socialnetworkprovider";
import {SocialNetworkProviderService} from "../../services/social-network-provider.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-socialprovider',
  templateUrl: './social-network-provider.component.html',
  styleUrls: ['./social-network-provider.component.scss']
})
export class SocialNetworkProviderComponent implements OnInit {

  public socialProviders : SocialNetworkProvider[] = [];
  public socialProvider : SocialNetworkProvider = new SocialNetworkProvider();

  dialog: boolean = false;
  submitted : boolean = false;
  displayInfoDialog: boolean = false;

  constructor(private socialProviderService : SocialNetworkProviderService
              , private messageService: MessageService
              , private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.socialProviderService.getAll().subscribe(data => {
      this.socialProviders = data
    });
  }
  openNewDialog() {
    this.socialProvider = new SocialNetworkProvider();
    this.submitted = false;
    this.dialog = true;
  }

  hideDialog(){
    this.submitted = false;
    this.dialog = false;
  }

  openEditDialog(socialProvider : SocialNetworkProvider){
    this.socialProvider = {...socialProvider};
    this.dialog = true;
  }

  showInfoDialog() {
    this.displayInfoDialog = true;
  }

  save(){
    this.submitted = true;

    this.socialProviderService.save(this.socialProvider).then(() =>{
      this.socialProviderService.getAll().subscribe(data => {
        this.socialProviders = data;
        this.dialog = false;
        this.socialProvider = new SocialNetworkProvider();
        console.log("Done with save");
      })
    });
  }

  delete(socialProvider : SocialNetworkProvider) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете? ' + socialProvider.name + '?',
      header: 'Потвърждаване',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.socialProviderService.delete(socialProvider).then(() => {
          this.socialProvider = new SocialNetworkProvider();
          console.log("Done with delete");
        });
      }
    });
  }

}
