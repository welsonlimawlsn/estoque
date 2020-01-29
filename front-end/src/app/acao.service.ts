import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoadingService } from './loading/loading.service';
import { tap } from 'rxjs/operators';
import { Mensagem, MensagemErroService } from './core/mensagem-erro/mensagem-erro.service';

@Injectable({
  providedIn: 'root'
})
export class AcaoService {

  constructor(private loadingService: LoadingService, private mensagemErroService: MensagemErroService) {
  }

  executa<T>(observable: Observable<T>, comFeedback: boolean = true): Observable<T> {
    this.mensagemErroService.limpaMensagens();
    this.loadingService.show();
    return observable.pipe(tap(response => {
      this.loadingService.hide();
      if (comFeedback) {
        this.mensagemErroService.apresentaMensagens([
          new Mensagem('Operação realizada com sucesso.', 'success')
        ]);
      }
    }));
  }
}
