import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { LoginClienteRequisicao } from './model/login-cliente-requisicao';
import { Observable } from 'rxjs';
import { LoginClienteResposta } from './model/login-cliente-resposta';
import { environment } from '../../environments/environment';
import { NovoClienteRequisicao } from './model/novo-cliente-requisicao';
import { ConsultaClientesRequisicao } from './model/consulta-clientes-requisicao';
import { ConsultaClientesResposta } from './model/consulta-clientes-resposta';

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

  listaClientes(requisicao: ConsultaClientesRequisicao): Observable<ConsultaClientesResposta> {
    let httpParams = new HttpParams();

    httpParams = this.appendHttpParams(requisicao, httpParams);

    if (requisicao.numeroPagina) {
      httpParams = httpParams.append('numeroPagina', requisicao.numeroPagina.toString());
    }

    if (requisicao.quantidadePorPagina) {
      httpParams = httpParams.append('quantidadePorPagina', requisicao.quantidadePorPagina.toString());
    }

    return this.http.get<ConsultaClientesResposta>(`${environment.backendUrl}/cliente`, {params: httpParams});
  }

  relatorioConsultaCliente(requisicao: ConsultaClientesRequisicao): Observable<any> {
    let httpParams = new HttpParams();

    httpParams = this.appendHttpParams(requisicao, httpParams);

    return this.http.get(`${environment.backendUrl}/cliente/relatorio`, {
        params: httpParams,
        responseType: 'blob'
      }
    );
  }

  private appendHttpParams(requisicao: ConsultaClientesRequisicao, httpParams: HttpParams) {
    if (requisicao.cpf) {
      httpParams = httpParams.append('cpf', requisicao.cpf);
    }

    if (requisicao.grupo) {
      httpParams = httpParams.append('grupo', requisicao.grupo.toString());
    }

    if (requisicao.nomeCliente) {
      httpParams = httpParams.append('nomeCliente', requisicao.nomeCliente);
    }
    return httpParams;
  }
}
