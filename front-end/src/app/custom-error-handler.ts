import { ErrorHandler, Injectable, NgZone } from '@angular/core';
import { LoadingService } from './loading/loading.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Mensagem, MensagemErroService } from './core/mensagem-erro/mensagem-erro.service';
import { SessaoService } from './sessao.service';

@Injectable()
export class CustomErrorHandler implements ErrorHandler {

  constructor(
    private loadingService: LoadingService,
    private mensagemErroService: MensagemErroService,
    private zone: NgZone,
    private sessaoService: SessaoService
  ) {
  }

  handleError(error: any): void {
    this.zone.run(() => {
        if (error instanceof HttpErrorResponse) {
          this.loadingService.hide();
          if (error.status === 0) {
            this.mensagemErroService.apresentaMensagens([
              new Mensagem('Erro interno, contate o administrador.', 'danger')
            ]);
          } else {
            this.mensagemErroService.apresentaMensagens(error.error.map(msg => new Mensagem(msg, 'danger')));
            if (error.status === 401) {
              this.sessaoService.logout();
            }
          }
        } else {
          console.error(error);
        }
      }
    );
  }

}
