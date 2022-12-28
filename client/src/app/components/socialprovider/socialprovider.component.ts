import { Component, OnInit } from '@angular/core';
import {Socialproviders} from "../../models/socialprovider";
import {SocialproviderService} from "../../services/socialprovider.service";
import {ConfirmationService, MessageService} from "primeng/api";

@Component({
  selector: 'app-socialprovider',
  templateUrl: './socialprovider.component.html',
  styleUrls: ['./socialprovider.component.scss']
})
export class SocialproviderComponent implements OnInit {

  socialProvidersDialog: boolean = false;

  public socialProviders : Socialproviders[] = [];

  public socialProvider : Socialproviders = new Socialproviders();

  selecetedSocialProviders : Socialproviders[] = [];

  submitted : boolean = false;

  constructor(private socialProviderService : SocialproviderService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.socialProviderService.getAll().subscribe(data => {
      this.socialProviders = data;
    });
  }
  openNew(){
    this.socialProvider = new Socialproviders();
    this.submitted = false;
    this.socialProvidersDialog = true;
  }
  editSocialProviders(socialProvider : Socialproviders){
    this.socialProvider = {...socialProvider};
    this.socialProvidersDialog = true;
  }
  deleteSocialProviders(socialProvider : Socialproviders) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + socialProvider.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.socialProviders = this.socialProviders.filter(val => val.id !== socialProvider.id);
        this.socialProviderService.delete(socialProvider).then(() => {
          this.socialProvider = new Socialproviders();
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
      this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
      if (!this.socialProvider.id) {
        this.socialProviders.push(this.socialProvider);
        this.socialProviders = [...this.socialProviders];
      }
      this.socialProvidersDialog = false;
      this.socialProvider = new Socialproviders();
    });
  }

}
