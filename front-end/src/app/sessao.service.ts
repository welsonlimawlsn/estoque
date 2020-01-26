import { Injectable } from '@angular/core';
import { Cliente } from './usuario/model/cliente';
import { LoginClienteResposta } from './usuario/model/login-cliente-resposta';
import { isNullOrUndefined } from 'util';

@Injectable({
  providedIn: 'root'
})
export class SessaoService {

  token: string;

  cliente: Cliente;

  private readonly TOKEN_CONST = 'token';

  private readonly CLIENTE_CONST = 'cliente';

  constructor() {
    this.recuperaSessao();
  }

  isAutenticado(): boolean {
    return !isNullOrUndefined(this.token) && !isNullOrUndefined(this.cliente);
  }

  iniciaSessao(resposta: LoginClienteResposta) {
    this.token = resposta.token;
    this.cliente = resposta.cliente;
    this.gravaSessao();
  }

  private gravaSessao() {
    sessionStorage.setItem(this.TOKEN_CONST, this.token);
    sessionStorage.setItem(this.CLIENTE_CONST, JSON.stringify(this.cliente));
  }

  private recuperaSessao() {
    this.token = sessionStorage.getItem(this.TOKEN_CONST);
    this.cliente = JSON.parse(sessionStorage.getItem(this.CLIENTE_CONST));
  }

  logout() {
    this.token = undefined;
    this.cliente = undefined;
    sessionStorage.removeItem(this.TOKEN_CONST);
    sessionStorage.removeItem(this.CLIENTE_CONST);
  }
}
