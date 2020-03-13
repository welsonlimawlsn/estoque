import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoadingComponent } from './loading/loading.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CustomHttpInterceptorService } from './custom-http-interceptor.service';
import { LoadingService } from './loading/loading.service';
import { CustomErrorHandler } from './custom-error-handler';
import { AcaoService } from './acao.service';
import { SessaoService } from './sessao.service';
import { GrupoService } from './grupo/grupo.service';
import { UsuarioService } from './usuario/usuario.service';
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import { CoreModule } from './core/core.module';
import { MensagemErroService } from './core/mensagem-erro/mensagem-erro.service';
import { HashLocationStrategy, LocationStrategy } from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    LoadingComponent,
    MenuComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CustomHttpInterceptorService,
      multi: true
    },
    {
      provide: ErrorHandler,
      useClass: CustomErrorHandler
    },
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    LoadingService,
    AcaoService,
    SessaoService,
    GrupoService,
    UsuarioService,
    MensagemErroService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
