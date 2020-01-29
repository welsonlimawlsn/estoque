import { AbstractControl, ValidationErrors } from '@angular/forms';

export class Validadores {

  static email(control: AbstractControl): ValidationErrors | null {
    if (control.value && !control.value.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
      return {['email']: true};
    }
    return null;
  }

  static cpf(control: AbstractControl): ValidationErrors | null {
    if (control.value) {
      if (control.value.length !== 11) {
        return {['cpf']: true};
      }
      if (Validadores.calculaDigitoMod11(control.value, 10) !== Number(control.value.charAt(9)) ||
        Validadores.calculaDigitoMod11(control.value, 11) !== Number(control.value.charAt(10))) {
        return {['cpf']: true};
      }
    }
    return null;
  }

  private static calculaDigitoMod11(v: string, inicioContagem: number, fimContagem: number = 2): number {
    let index = 0;
    let soma = 0;
    for (let i = inicioContagem; i >= fimContagem; i--) {
      soma += Number(v.charAt(index++)) * i;
    }
    const resto = soma % 11;
    return resto <= 1 ? 0 : 11 - resto;
  }
}
