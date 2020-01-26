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
import { MensagemErroComponent } from './mensagem-erro/mensagem-erro.component';
import { MensagemErroService } from './mensagem-erro/mensagem-erro.service';

@NgModule({
  declarations: [
    AppComponent,
    LoadingComponent,
    MensagemErroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
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
    LoadingService,
    AcaoService,
    MensagemErroService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
