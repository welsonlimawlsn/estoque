import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListaFuncionalidadesResposta } from './model/lista-funcionalidades-resposta';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FuncionalidadeService {

  constructor(private http: HttpClient) {
  }

  listaFuncionalidades(): Observable<ListaFuncionalidadesResposta> {
    return this.http.get<ListaFuncionalidadesResposta>(`${environment.backendUrl}/funcionalidade`);
  }
}
