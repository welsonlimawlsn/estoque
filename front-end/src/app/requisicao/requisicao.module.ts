import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConsultaComponent } from './consulta/consulta.component';
import { RouterModule } from "@angular/router";
import { AuthGuardService } from "../auth-guard.service";
import { FormularioModule } from "../formulario/formulario.module";
import {CoreModule} from "../core/core.module";


@NgModule({
  declarations: [ConsultaComponent],
    imports: [
        CommonModule,
        RouterModule.forChild([
            {
                path: 'consulta',
                component: ConsultaComponent,
                canActivate: [AuthGuardService]
            }
        ]),
        FormularioModule,
        CoreModule
    ]
})
export class RequisicaoModule {
}
