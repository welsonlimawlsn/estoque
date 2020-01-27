import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListaGruposResposta } from './model/lista-grupos-resposta';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { NovoGrupoRequisicao } from './model/novo-grupo-requisicao';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  constructor(private http: HttpClient) {
  }

  listaGrupos(): Observable<ListaGruposResposta> {
    return this.http.get<ListaGruposResposta>(`${environment.backendUrl}/grupo`);
  }

  novoGrupo(requisicao: NovoGrupoRequisicao): Observable<any> {
    return this.http.post(`${environment.backendUrl}/grupo`, requisicao);
  }

}
