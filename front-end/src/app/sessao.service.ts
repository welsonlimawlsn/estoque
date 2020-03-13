import { EventEmitter, Injectable, Injector } from '@angular/core';
import { Cliente } from './usuario/model/cliente';
import { LoginClienteResposta } from './usuario/model/login-cliente-resposta';
import { isNullOrUndefined } from 'util';
import { Menu } from './menu/model/menu';
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class SessaoService {

  token: string;

  cliente: Cliente;

  menu: Menu[];

  menuEvent = new EventEmitter<Menu[]>();

  private readonly TOKEN_CONST = 'token';
  private readonly CLIENTE_CONST = 'cliente';
  private readonly MENU_CONST = 'menu';

  constructor(private injector: Injector) {
    this.recuperaSessao();
  }

  isAutenticado(): boolean {
    return !isNullOrUndefined(this.token) && !isNullOrUndefined(this.cliente);
  }

  iniciaSessao(resposta: LoginClienteResposta) {
    this.token = resposta.token;
    this.cliente = resposta.cliente;
    this.menu = resposta.menu;
    this.gravaSessao();
    this.menuEvent.emit(this.menu);
  }

  logout() {
    this.token = undefined;
    this.cliente = undefined;
    this.menu = undefined;
    sessionStorage.removeItem(this.TOKEN_CONST);
    sessionStorage.removeItem(this.CLIENTE_CONST);
    sessionStorage.removeItem(this.MENU_CONST);
    this.menuEvent.emit(undefined);
    this.router.navigate(['usuario', 'login']);
  }

  isAutorizado(rota: string): boolean {
    let caminho = this.menu.filter(menu => menu.caminho).map(menu => menu.caminho);
    caminho = caminho.concat(this.menu
      .map(menu => menu.menusFilhos)
      .reduce((previousValue, currentValue) => previousValue.concat(currentValue), [])
      .map(menu => menu.caminho));
    return caminho.indexOf(rota) !== -1;
  }

  private gravaSessao() {
    sessionStorage.setItem(this.TOKEN_CONST, this.token);
    sessionStorage.setItem(this.CLIENTE_CONST, JSON.stringify(this.cliente));
    sessionStorage.setItem(this.MENU_CONST, JSON.stringify(this.menu));
  }

  private recuperaSessao() {
    this.token = sessionStorage.getItem(this.TOKEN_CONST);
    this.cliente = JSON.parse(sessionStorage.getItem(this.CLIENTE_CONST));
    this.menu = JSON.parse(sessionStorage.getItem(this.MENU_CONST));
    if (this.isAutenticado()) {
      this.menuEvent.emit(this.menu);
    }
  }

  get router() {
    return this.injector.get(Router);
  }
}
