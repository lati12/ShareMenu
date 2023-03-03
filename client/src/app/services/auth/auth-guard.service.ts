import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import {AuthService} from "./auth.service";
import {TokenStorageService} from "./token-storage.service";

//механизъм за детекване на роля и до какво има достъп юзъра в потребителската част на системата

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService : AuthService,
    private storageService: TokenStorageService,
    private router: Router
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isTokenExpired(this.storageService.getAccessToken())) {
      this.router.navigate(['login']).catch(console.error);
      return false;
    }

    return this.detectUserRole(route, state);
  }

  detectUserRole(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let user = this.authService.getUser();
    if (user === null)
      return false;

    let userRoles = user.roles;
    let requiredRoles = route.data.roles;

    if (!userRoles) {
      return false;
    }

    try {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < requiredRoles.length; j++) {
          if (userRoles[i] == requiredRoles[j]) {
            return true;
          }
        }
      }
    } catch (e) {
      console.log(e);
    }

    return false;
  }
}
