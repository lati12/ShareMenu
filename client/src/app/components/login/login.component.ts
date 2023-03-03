import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";
import {LoginRequest} from "../../common/login-request";
import {Roles} from "../../common/Roles";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {NotificationService} from "../../services/notification.service";

// Компонентът Login служи за влизането на потребител в своя акаунт

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private loginRequest: LoginRequest = new LoginRequest();

  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.minLength(3)]);

  signin: FormGroup = new FormGroup({
    email: this.email,
    password: this.password
  });

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: Roles[] = [];

  constructor(private authService: AuthService, public router: Router,
              private notificationService: NotificationService) { }

  async onSubmit() {
    const formData = new FormData();
    formData.append('email', this.signin.get('email')?.value);
    formData.append('password', this.signin.get('password')?.value);

    await this.authService.login(formData).then(() => {
      this.router.navigate(['']).catch(console.error);
    }).catch(ex => {
      this.notificationService.notification$.next({severity: 'error', summary: 'Проблем с логване', detail: 'Грешни данни'})
    });
  }

  redirectRegister() {
    this.router.navigate(['/register']).catch(console.error);
  }

  ngOnInit(): void {
  }
}
