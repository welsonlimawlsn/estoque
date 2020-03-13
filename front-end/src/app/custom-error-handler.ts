import { ErrorHandler, Injectable, NgZone } from '@angular/core';
import { LoadingService } from './loading/loading.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Mensagem, MensagemErroService, TipoMensagem } from './core/mensagem-erro/mensagem-erro.service';
import { SessaoService } from './sessao.service';
import { HttpStatusCode } from "./enum/enums";

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
          if (error.status === HttpStatusCode.UNDEFINED) {
            this.mensagemErroService.apresentaMensagens([
              new Mensagem('Erro interno, contate o administrador.', TipoMensagem.DANGER)
            ]);
          } else {
            if (error.status === HttpStatusCode.UNAUTHORIZED || error.status === HttpStatusCode.FORBIDDEN) {
              this.sessaoService.logout();
            }
            this.mensagemErroService.apresentaMensagens(error.error.map(msg => new Mensagem(msg, TipoMensagem.DANGER)));
          }
        } else {
          console.error(error);
        }
      }
    );
  }

}
