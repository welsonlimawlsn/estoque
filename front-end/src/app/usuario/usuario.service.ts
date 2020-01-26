import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginClienteRequisicao } from './model/login-cliente-requisicao';
import { Observable } from 'rxjs';
import { LoginClienteResposta } from './model/login-cliente-resposta';
import { environment } from '../../environments/environment';
import { NovoClienteRequisicao } from './model/novo-cliente-requisicao';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) {
  }

  loginCliente(requisicao: LoginClienteRequisicao): Observable<LoginClienteResposta> {
    return this.http.post<LoginClienteResposta>(`${environment.backendUrl}/cliente/login`, requisicao);
  }

  cadastraCliente(requisicao: NovoClienteRequisicao): Observable<any> {
    return this.http.post(`${environment.backendUrl}/cliente`, requisicao);
  }
}
