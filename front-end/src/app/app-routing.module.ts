import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthGuardService } from './auth-guard.service';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'usuario', loadChildren: './usuario/usuario.module#UsuarioModule',
  },
  {
    path: 'grupo', loadChildren: './grupo/grupo.module#GrupoModule',
  },
  {
    path: 'requisicao', loadChildren: './requisicao/requisicao.module#RequisicaoModule'
  },
  {
    path: '', redirectTo: 'home', pathMatch: 'prefix'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
