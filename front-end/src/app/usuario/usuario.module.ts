import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from './usuario.service';

const routes = [
  {
    path: 'login', component: LoginComponent
  }
];

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ],
  providers: [
    UsuarioService
  ]
})
export class UsuarioModule {
}
