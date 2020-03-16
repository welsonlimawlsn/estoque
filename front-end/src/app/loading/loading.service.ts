import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  contador = 0;

  constructor() {
  }

  private _emitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  get emitter(): EventEmitter<boolean> {
    return this._emitter;
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
  }
}
