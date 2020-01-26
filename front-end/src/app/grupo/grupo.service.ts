import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListaGruposResposta } from './model/lista-grupos-resposta';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  constructor(private http: HttpClient) {
  }

  listaGrupos(): Observable<ListaGruposResposta> {
    return this.http.get<ListaGruposResposta>(`${environment.backendUrl}/grupo`);
  }
}
