import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MensagemErroService {

  constructor() {
  }

  private _emmiter: EventEmitter<Mensagem[]> = new EventEmitter<Mensagem[]>();

  get emmiter(): EventEmitter<Mensagem[]> {
    return this._emmiter;
  }

  apresentaMensagens(mensagens: Mensagem[]) {
    this._emmiter.emit(mensagens);
  }

  limpaMensagens() {
    this._emmiter.emit([]);
  }
}

export class Mensagem {
  mensagem: string;
  tipo: string;

  constructor(mensagem: string, tipo: string) {
    this.mensagem = mensagem;
    this.tipo = tipo;
  }
}
