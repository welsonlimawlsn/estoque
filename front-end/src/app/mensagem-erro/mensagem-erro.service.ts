import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MensagemErroService {

  private _emmiter: EventEmitter<string[]> = new EventEmitter<string[]>();

  constructor() {
  }


  get emmiter(): EventEmitter<string[]> {
    return this._emmiter;
  }

  apresentaMensagens(mensagens: string[]) {
    this._emmiter.emit(mensagens);
  }

  limpaMensagens() {
    this._emmiter.emit([]);
  }
}
