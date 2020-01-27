import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NovoGrupoComponent } from './novo-grupo/novo-grupo.component';
import { FormularioModule } from '../formulario/formulario.module';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [NovoGrupoComponent],
  imports: [
    CommonModule,
    FormularioModule,
    RouterModule.forChild([
      {path: 'novo', component: NovoGrupoComponent}
    ]),
    ReactiveFormsModule
  ]
})
export class GrupoModule {
}
