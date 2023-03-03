import { Component, OnInit } from '@angular/core';
import {SocialNetworkConnectivity} from "../../common/socialNetworkConnectivity";
import {SocialNetworkConnectivityService} from "../../services/social-network-connectivity.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {NotificationService} from "../../services/notification.service";

// В компонентът SocialNetworkProvider са имлементирани CRUD операции и комуникацията със сървъра.

@Component({
  selector: 'app-socialconnectivity',
  templateUrl: './social-network-connectivity.component.html',
  styleUrls: ['./social-network-connectivity.component.scss']
})
export class SocialNetworkConnectivityComponent implements OnInit {

  socialProvidersDialog: boolean = false;

  public socialProviders : SocialNetworkConnectivity[] = [];

  public socialNetworkConnectivity : SocialNetworkConnectivity = new SocialNetworkConnectivity();

  submitted : boolean = false;

  constructor(private socialProviderService : SocialNetworkConnectivityService,
              private notificationService : NotificationService
              , private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.socialProviderService.getAll().subscribe(data => {
      this.socialProviders = data;
    });
  }
  openNew(){
    this.socialNetworkConnectivity = new SocialNetworkConnectivity();
    this.submitted = false;
    this.socialProvidersDialog = true;
  }

  hideDialog(){
    this.submitted = false;
    this.socialProvidersDialog = false;
  }

  saveSocialProvider(){
    this.submitted = true;

    this.socialProviderService.getPage(this.socialNetworkConnectivity).then(() =>{
      this.socialProviderService.getAll().subscribe(data => {
        this.socialProvidersDialog = false;
        this.socialNetworkConnectivity = new SocialNetworkConnectivity();
      })
    }).catch(ex =>{
      this.notificationService.notification$.next({severity: 'error', summary: 'Моля попълнете данни', detail: ex.error});
    })
  }

  deleteSocialProviders(socialProvider : SocialNetworkConnectivity) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете? ' + socialProvider.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.socialProviders = this.socialProviders.filter(val => val.id !== socialProvider.id);
        this.socialProviderService.delete(socialProvider).then(() => {
          this.socialNetworkConnectivity = new SocialNetworkConnectivity();
        });
      }
    });
  }
}
