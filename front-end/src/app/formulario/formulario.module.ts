import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MultiSelectComponent } from './multi-select/multi-select.component';
import { InputTextComponent } from './input-text/input-text.component';
import { FormsModule } from '@angular/forms';
import { InputCpfComponent } from './input-cpf/input-cpf.component';
import { SelectComponent } from './select/select.component';
import { BotaoDownloadRelatorioComponent } from './botao-download-relatorio/botao-download-relatorio.component';


@NgModule({
  declarations: [MultiSelectComponent, InputTextComponent, InputCpfComponent, SelectComponent, BotaoDownloadRelatorioComponent],
  exports: [
    MultiSelectComponent,
    InputTextComponent,
    InputCpfComponent,
    SelectComponent,
    BotaoDownloadRelatorioComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class FormularioModule {
}
