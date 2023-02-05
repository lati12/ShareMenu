import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import {AuthService} from "./auth.service";
import {StorageService} from "./storage.service";

@Injectable({
    providedIn: 'root',
})
export class AuthGuard implements CanActivate {
    constructor(
      private authService : AuthService,
        private storageService: StorageService,
        private router: Router
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.detectUserRole(route, state);
    }

    detectUserRole(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let userRoles = this.storageService.getUser().roles;
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