import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { Route, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CadastroComponent } from './cadastro/cadastro.component';
import { AuthGuardService } from '../auth-guard.service';
import { FormularioModule } from '../formulario/formulario.module';
import { CoreModule } from '../core/core.module';
import { ConsultaComponent } from './consulta/consulta.component';

const routes: Route[] = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'cadastro',
    component: CadastroComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'consulta',
    component: ConsultaComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  declarations: [LoginComponent, CadastroComponent, ConsultaComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    FormularioModule,
    CoreModule
  ]
})
export class UsuarioModule {
}
