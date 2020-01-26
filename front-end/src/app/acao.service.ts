import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoadingService } from './loading/loading.service';
import { tap } from 'rxjs/operators';
import { MensagemErroService } from './mensagem-erro/mensagem-erro.service';

@Injectable({
  providedIn: 'root'
})
export class AcaoService {

  constructor(private loadingService: LoadingService, private mensagemErroService: MensagemErroService) {
  }

  executa<T>(observable: Observable<T>): Observable<T> {
    this.mensagemErroService.limpaMensagens();
    this.loadingService.show();
    return observable.pipe(tap(response => this.loadingService.hide()));
  }
}
