import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Users} from "../../common/users";
import {filter} from "rxjs";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  counter = 95;
  hideRegFields = false;
  user: Users = new Users();


  email = new FormControl("", [Validators.email, Validators.required])
  password = new FormControl('', [Validators.required, Validators.minLength(3)])
  passwordConfirm = new FormControl('', [Validators.required])
  firstName = new FormControl('', [Validators.required]);
  lastName = new FormControl('', [Validators.required]);

  companyName = new FormControl('', [Validators.required]);
  registerFormGroup: FormGroup = new FormGroup({
    email: this.email,
    password: this.password,
    firstName: this.firstName,
    lastName: this.lastName,
    passwordConfirm: this.passwordConfirm,
    companyName: this.companyName
  });

  constructor(private authService : AuthService, private router: Router) {
  }
  ngOnInit() : void {
  }

  redirectLogin() {
    this.router.navigate(['login']).catch(console.error);
  }

  async doRegister() {
    window.scroll(0, 0)
    if(this.email.value != null)
      this.user.email = this.email.value;

    if(this.password.value != null)
      this.user.password = this.password.value;

    if(this.firstName.value != null)
      this.user.name = this.firstName.value;

    if(this.lastName.value != null)
      this.user.lastname = this.lastName.value;

    if(this.companyName.value != null)
      this.user.companyname = this.companyName.value;

    try {
      await this.authService.register(this.user)
      this.hideRegFields = true;
      this.startCounter()
    } catch (ex: any) {
      //this.notificationService.notification$.next({severity: 'error', summary: "Registration error", detail: ex.error})
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
