import { ErrorHandler, Injectable, NgZone } from '@angular/core';
import { LoadingService } from './loading/loading.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MensagemErroService } from './mensagem-erro/mensagem-erro.service';

@Injectable()
export class CustomErrorHandler implements ErrorHandler {

  constructor(private loadingService: LoadingService, private mensagemErroService: MensagemErroService, private zone: NgZone) {
  }

  handleError(error: any): void {
    this.zone.run(() => {
      this.loadingService.hide();
      if (error instanceof HttpErrorResponse) {
        this.mensagemErroService.apresentaMensagens(error.error);
      }
    });
  }

}
