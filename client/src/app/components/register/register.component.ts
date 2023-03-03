  import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {RegisterRequest} from "../../common/register-request";
import {NotificationService} from "../../services/notification.service";

// Компонентът Register служи за регистрирването на потребител в апликацията

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  counter = 95;
  hideRegFields = false;
  registerRequest = new RegisterRequest();

  email = new FormControl("", [Validators.email, Validators.required])
  password = new FormControl('', [Validators.required, Validators.minLength(3)])
  passwordConfirm = new FormControl('', [Validators.required])
  name = new FormControl('', [Validators.required]);
  lastName = new FormControl('', [Validators.required]);

  companyName = new FormControl('', [Validators.required]);
  registerFormGroup: FormGroup = new FormGroup({
    email: this.email,
    password: this.password,
    name: this.name,
    lastName: this.lastName,
    passwordConfirm: this.passwordConfirm,
    companyName: this.companyName
  });

  constructor(private authService : AuthService,
              private router: Router,
              private notificationService : NotificationService) {
  }
  ngOnInit() : void {
  }

  redirectLogin() {
    this.router.navigate(['login']).catch(console.error);
  }

  async doRegister() {
    window.scroll(0, 0)
    if(this.email.value != null)
      this.registerRequest.email = this.email.value

    if(this.password.value != null)
      this.registerRequest.password = this.password.value;

    if(this.name.value != null)
      this.registerRequest.name = this.name.value;

    if(this.lastName.value != null)
      this.registerRequest.lastName = this.lastName.value;

    if(this.companyName.value != null)
      this.registerRequest.companyName = this.companyName.value;

    try {
      debugger
      await this.authService.register(this.registerRequest).then(() => {
        this.hideRegFields = true;
        this.startCounter()
      }).catch(ex => {
        this.notificationService.notification$.next({severity: 'error', summary: 'Моля попълнете данни', detail: ex.error});
      })
    } catch (ex: any) {
      console.log(ex);
    }
  }

  startCounter() {
    let interval = setInterval(() => {
      this.counter -= 20
      if(this.counter <= 0) {
        clearInterval(interval);
        this.redirectLogin();
      }
    }, 1000);
  }
}
