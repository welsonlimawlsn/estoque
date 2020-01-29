import { Component, Input } from '@angular/core';
import { ControlValueAccessor, FormGroup, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-input-text',
  templateUrl: './input-text.component.html',
  styleUrls: ['./input-text.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: InputTextComponent,
      multi: true
    }
  ]
})
export class InputTextComponent implements ControlValueAccessor {
  @Input()
  id: string;

  @Input()
  label: string;

  @Input()
  value: string;

  change: any;

  touched: any;

  @Input()
  formGroup: FormGroup;

  @Input()
  formControlName: string;

  @Input()
  type = 'text';

  constructor() {
  }

  registerOnChange(fn: any): void {
    this.change = fn;
  }

  registerOnTouched(fn: any): void {
    this.touched = fn;
  }

  writeValue(obj: any): void {
    this.value = obj;
  }

  updateChange() {
    this.change(this.value);
  }

  errors() {
    return this.getAbstractControl().errors;
  }

  private getAbstractControl() {
    return this.formGroup.get(this.formControlName);
  }

  dirty() {
    return this.getAbstractControl().touched || this.getAbstractControl().dirty;
  }

  updateTouched() {
    this.touched(this.value);
  }
}
