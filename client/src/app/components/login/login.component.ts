import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";
import {LoginRequest} from "../../common/login-request";
import {Roles} from "../../common/Roles";
import {FormControl, FormGroup, Validators} from "@angular/forms";

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

  constructor(private authService: AuthService, public router: Router, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  redirectRegister() {
    this.router.navigate(['/register']).catch(console.error);
  }

  async onSubmit() {
    this.loginRequest.email = this.signin.get('email')?.value;
    this.loginRequest.password = this.signin.get('password')?.value;

    try {
      await this.authService.login(this.loginRequest).then((result) => {
        this.tokenStorage.saveToken(result.jwt);
        this.tokenStorage.saveUser(result)
        this.isLoggedIn = true;
        this.isLoginFailed = false;
        this.reloadPage();
      }).catch(reason => {
        this.isLoginFailed = true;
        console.log(reason);
      });
    } catch (ex: any) {
      console.log(ex);
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}
