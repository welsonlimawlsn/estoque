import { Component } from '@angular/core';
import { InputTextComponent } from '../input-text/input-text.component';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-input-cpf',
  templateUrl: './input-cpf.component.html',
  styleUrls: ['./input-cpf.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: InputCpfComponent,
      multi: true
    }
  ]
})
export class InputCpfComponent extends InputTextComponent {

  private readonly naoNumeroExcecaoPontuacao = /[^\d.-]/g;
  private readonly naoNumero = /[^\d]/g;

  aplicaMascara() {
    switch (this.value.length) {
      case 3:
        this.value += '.';
        break;
      case 7:
        this.value += '.';
        break;
      case 11:
        this.value += '-';
        break;
    }
  }


  updateChange() {
    this.value = this.value.replace(this.naoNumeroExcecaoPontuacao, '');
    this.change(this.value.replace(this.naoNumero, ''));
  }


  updateTouched() {
    this.touched(this.value.replace(this.naoNumero, ''));
  }
}
