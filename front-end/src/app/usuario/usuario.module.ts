import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from './usuario.service';
import { CadastroComponent } from './cadastro/cadastro.component';

const routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'cadastro', component: CadastroComponent
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
