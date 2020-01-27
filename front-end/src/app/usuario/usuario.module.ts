import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { Route, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CadastroComponent } from './cadastro/cadastro.component';
import { AuthGuardService } from '../auth-guard.service';

const routes: Route[] = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'cadastro',
    component: CadastroComponent,
    canActivate: [AuthGuardService]
  }
];

@NgModule({
  declarations: [LoginComponent, CadastroComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ]
})
export class UsuarioModule {
}
