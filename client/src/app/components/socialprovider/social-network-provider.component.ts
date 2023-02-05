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

  socialProvidersDialog: boolean = false;

  public socialProviders : SocialNetworkProvider[] = [];

  public socialProvider : SocialNetworkProvider = new SocialNetworkProvider();

  submitted : boolean = false;

  constructor(private socialProviderService : SocialNetworkProviderService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.socialProviderService.getAll().subscribe(data => {
      this.socialProviders = data;
    });
  }
  openNew(){
    this.socialProvider = new SocialNetworkProvider();
    this.submitted = false;
    this.socialProvidersDialog = true;
  }
  editSocialProviders(socialProvider : SocialNetworkProvider){
    this.socialProvider = {...socialProvider};
    this.socialProvidersDialog = true;
  }
  deleteSocialProviders(socialProvider : SocialNetworkProvider) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + socialProvider.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.socialProviders = this.socialProviders.filter(val => val.id !== socialProvider.id);
        this.socialProviderService.delete(socialProvider).then(() => {
          this.socialProvider = new SocialNetworkProvider();
        });
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }
  hideDialog(){
    this.submitted = false;
    this.socialProvidersDialog = false;
  }
  saveSocialProvider(){
    this.submitted = true;

    this.socialProviderService.save(this.socialProvider).then(() =>{
      this.socialProviderService.getAll().subscribe(data => {
        this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
        this.socialProvidersDialog = false;
        this.socialProvider = new SocialNetworkProvider();
      })
    });
  }

}
