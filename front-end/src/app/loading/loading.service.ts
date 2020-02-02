import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private _emitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  contador = 0;

  constructor() {
  }

  show() {
    if (this.contador++ === 0) {
      this._emitter.emit(true);
    }
  }

  hide() {
    if (--this.contador === 0) {
      this._emitter.emit(false);
    }
    console.log(this.contador);
  }

  get emitter(): EventEmitter<boolean> {
    return this._emitter;
  }
}
