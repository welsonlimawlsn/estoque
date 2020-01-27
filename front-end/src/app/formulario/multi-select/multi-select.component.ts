import { Component, Input, OnInit } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-multi-select',
  templateUrl: './multi-select.component.html',
  styleUrls: ['./multi-select.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: MultiSelectComponent,
      multi: true
    }
  ]
})
export class MultiSelectComponent implements OnInit, ControlValueAccessor {

  @Input()
  label: string;

  @Input()
  opcoes: Opcao[];

  change: any;

  touched: any;

  constructor() {
  }

  ngOnInit() {
  }

  registerOnChange(fn: any): void {
    this.change = fn;
  }

  registerOnTouched(fn: any): void {
    this.touched = fn;
  }

  writeValue(obj: any[]): void {
    if (this.opcoes) {
      this.opcoes.filter(opcao => obj.indexOf(opcao.key) !== -1).forEach(opcao => opcao.selected = true);
    }
  }

  update() {
    const codigos = this.opcoes.filter(opcao => opcao.selected).map(opcao => opcao.key);
    this.change(codigos);
    this.touched(codigos);
  }

}

export class Opcao {
  key: any;
  value: string;
  selected = false;

  constructor(key: any, value: string) {
    this.key = key;
    this.value = value;
  }

  toggle() {
    this.selected = !this.selected;
  }
}
