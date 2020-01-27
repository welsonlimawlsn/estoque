import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
    path: 'usuario', loadChildren: './usuario/usuario.module#UsuarioModule',
  },
  {
    path: 'grupo', loadChildren: './grupo/grupo.module#GrupoModule',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
