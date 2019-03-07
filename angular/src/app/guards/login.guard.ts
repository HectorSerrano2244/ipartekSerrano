import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AutorizacionService } from '../providers/autorizacion.service';

@Injectable({
  providedIn: 'root'
})

export class LoginGuard implements CanActivate {
  constructor(private autorizacionService: AutorizacionService, private router: Router) {
    console.trace('LoginGuard constructor');
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (this.autorizacionService.isLogged()) {
      console.trace('guarda');
      console.debug('Estamos autorizados');
      console.debug('El agente en sessionStorage %o', this.autorizacionService.getAgente());
      return true;

    } else {
      console.warn('NO estamos autorizados');
      this.router.navigate(['/login']);
      return false;
    }
  }
}