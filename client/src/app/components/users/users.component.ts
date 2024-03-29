import { Component, OnInit } from '@angular/core';
import {Users} from "../../common/users";
import {UsersService} from "../../services/users.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {NotificationService} from "../../services/notification.service";

// В компонентът Users са имлементирани CRUD операции и комуникацията със сървъра.


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  usersDialog: boolean =false;

  public users : Users[] = [];

  public user : Users = new Users();

  submitted: boolean = false;

  constructor(private usersService : UsersService,
              private notificationService : NotificationService,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.usersService.getAll().subscribe(data => {
      this.users = data;
    })
  }
  openNew(){
    this.user = new Users();
    this.submitted = false;
    this.usersDialog = true;
  }

  hideDialog(){
    this.usersDialog = false;
    this.submitted = false;
  }

  saveUsers() {
    this.submitted = true;
    this.usersService.save(this.user).then(() => {
      this.usersService.getAll().subscribe(data => {
        this.usersDialog = false;
        this.user = new Users();
        console.log("Done with save");
      });
      debugger
    }).catch(ex =>{
      this.notificationService.notification$.next({severity: 'error', summary: 'Моля попълнете данните', detail: ex.error});
    })
  }

  editUsers(user: Users){
    this.user = {...user};
    this.usersDialog = true;
  }
  deleteUsers(user :Users) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриете? ' + user.name + '?', header: 'Confirm', icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.users = this.users.filter(val => val.id !== user.id);
        this.usersService.delete(user).then(() =>{
          this.user = new Users();
        });
      }
    });
  }

}
