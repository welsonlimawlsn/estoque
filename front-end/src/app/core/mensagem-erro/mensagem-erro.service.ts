import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MensagemErroService {

  private mensagens: Mensagem[] = [];

  emmiter: EventEmitter<Mensagem[]> = new EventEmitter<Mensagem[]>();

  constructor() {
  }

  apresentaMensagens(mensagens: Mensagem[]) {
    this.limpaMensagens();
    mensagens.forEach(mensagem => this.mensagens.push(mensagem));
    this.emmiter.emit(this.mensagens);
  }

  limpaMensagens() {
    this.mensagens = [];
  }
}

export class Mensagem {
  mensagem: string;
  tipo: string;

  constructor(mensagem: string, tipo: TipoMensagem) {
    this.mensagem = mensagem;
    this.tipo = tipo;
  }
}

export enum TipoMensagem {
  DANGER = 'danger',
  SUCCESS = 'success'
}
