import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {StorageService} from "../../services/storage.service";
import {MenuItem, MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.minLength(3)]);

  signin: FormGroup = new FormGroup({
    email: this.email,
    password: this.password
  });

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];


  constructor(private authService: AuthService, private router: Router, private storageService: StorageService,private messageService : MessageService) { }

  ngOnInit(): void {
  }

  redirectRegister() {
    this.router.navigate(['/register']).catch(console.error);
  }

  onSubmit(): void {
    this.authService.login(this.signin.get('email')?.value, this.signin.get('password')?.value).subscribe({
      next: data => {
        this.storageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getUser().roles;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
