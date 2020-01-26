import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private _emitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() {
  }

  show() {
    this._emitter.emit(true);
  }

  hide() {
    this._emitter.emit(false);
  }

  get emitter(): EventEmitter<boolean> {
    return this._emitter;
  }
}
