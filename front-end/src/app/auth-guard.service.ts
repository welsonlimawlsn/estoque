import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SessaoService } from './sessao.service';

@Injectable({
  providedIn: 'root'
})
export class  AuthGuardService implements CanActivate {

  constructor(private sessaoService: SessaoService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.sessaoService.isAutenticado()) {
      this.router.navigate(['usuario', 'login']);
    }
    if (state.url === '/home') {
      return this.sessaoService.isAutenticado();
    } else {
      return this.sessaoService.isAutenticado() && this.sessaoService.isAutorizado(state.url);
    }
  }
}
