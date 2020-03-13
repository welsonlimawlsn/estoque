import { Component, Input } from '@angular/core';
import { ControlValueAccessor, FormGroup, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: SelectComponent,
      multi: true
    }
  ]
})
export class SelectComponent implements ControlValueAccessor {
  value: Option;

  @Input()
  options: Option[];

  @Input()
  formControlName: string;

  @Input()
  formGroup: FormGroup;

  change: any;

  touched: any;

  @Input()
  label: string;

  constructor() {
  }

  select(option: Option = new Option(null, 'Escolha uma opção:')) {
    this.change(option.key);
    this.touched(option.key);
    this.value = option;
  }

  registerOnChange(fn: any): void {
    this.change = fn;
  }

  registerOnTouched(fn: any): void {
    this.touched = fn;
  }

  writeValue(obj: any): void {
    if (this.options) {
      this.value = this.options.filter(option => option.key === obj)[0];
    }
  }

  errors() {
    return this.getAbstractControl().errors;
  }

  dirty() {
    return this.getAbstractControl().touched || this.getAbstractControl().dirty;
  }

  private getAbstractControl() {
    return this.formGroup.get(this.formControlName);
  }
}

export class Option {
  key: any;
  value: string;

  constructor(key: any, value: string) {
    this.key = key;
    this.value = value;
  }
}
