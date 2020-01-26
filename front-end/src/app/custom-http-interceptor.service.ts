import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SessaoService } from './sessao.service';

@Injectable({
  providedIn: 'root'
})
export class CustomHttpInterceptorService implements HttpInterceptor {

  constructor(private sessaoService: SessaoService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.sessaoService.isAutenticado()) {
      let headersWithAuthorization = req.headers;
      headersWithAuthorization = headersWithAuthorization.set('Authorization', this.sessaoService.token);
      req = req.clone({
        headers: headersWithAuthorization
      });
    }
    return next.handle(req);
  }
}
