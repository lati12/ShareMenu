import { Component, OnInit } from '@angular/core';
import {Users} from "../../common/users";
import {UsersService} from "../../services/users.service";
import {ConfirmationService, MessageService} from "primeng/api";

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

  constructor(private usersService : UsersService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

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
        this.messageService.add({severity: 'success', summary: 'Успешно запазване', detail: 'Потребителят е запазен', life: 3000});
        this.usersDialog = false;
        this.user = new Users();
      });
    });
  }

  editUsers(user: Users){
    this.user = {...user};
    this.usersDialog = true;
  }
  deleteUsers(user :Users) {
    this.confirmationService.confirm({
      message: 'Наистина ли искате да изтриетв? ' + user.name + '?', header: 'Confirm', icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.users = this.users.filter(val => val.id !== user.id);
        this.usersService.delete(user).then(() =>{
          this.user = new Users();
        });
        this.messageService.add({severity:'success', summary: 'Успешно изтрит', detail: 'Потребителят е изтрит', life: 3000});
      }
    });
  }

}
