import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NovoGrupoComponent } from './novo-grupo/novo-grupo.component';
import { FormularioModule } from '../formulario/formulario.module';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthGuardService } from '../auth-guard.service';
import { CoreModule } from '../core/core.module';


@NgModule({
  declarations: [NovoGrupoComponent],
  imports: [
    CommonModule,
    FormularioModule,
    RouterModule.forChild([
      {path: 'novo', component: NovoGrupoComponent, canActivate: [AuthGuardService]}
    ]),
    ReactiveFormsModule,
    CoreModule
  ]
})
export class GrupoModule {
}
