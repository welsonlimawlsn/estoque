import {Component, Input, OnInit} from '@angular/core';
import {AcaoService} from "../../acao.service";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {HttpUtilService} from "../../core/http-util.service";
import {SessaoService} from "../../sessao.service";
import {Mensagem, MensagemErroService, TipoMensagem} from "../../core/mensagem-erro/mensagem-erro.service";
import {LoadingService} from "../../loading/loading.service";
import {Header, HttpStatusCode} from "../../enum/enums";

@Component({
  selector: 'app-botao-download-relatorio',
  templateUrl: './botao-download-relatorio.component.html',
  styleUrls: ['./botao-download-relatorio.component.scss']
})
export class BotaoDownloadRelatorioComponent implements OnInit {

  @Input()
  parametros: Map<string, string>;

  @Input()
  baseUrl: string;

  @Input()
  disabled: boolean = false;

  constructor(
    private acaoService: AcaoService,
    private http: HttpClient,
    private httpUtilService: HttpUtilService,
    private sessaoService: SessaoService,
    private mensagemErroService: MensagemErroService,
    private loadingService: LoadingService
  ) {
  }

  ngOnInit() {
  }

  async download(formato: string) {

    this.loadingService.show();

    let http = new XMLHttpRequest();

    let url = this.criaUrl(formato);

    let resposta = await this.acaoService.executa(
      this.http.get<any>(url)
    ).toPromise();

    let nomeRelatorioGerado = resposta.nomeRelatorioGerado;

    http.open('get', `${environment.backendUrl}/relatorio/${nomeRelatorioGerado}`);

    http.responseType = 'blob';

    if (this.sessaoService.isAutenticado()) {
      http.setRequestHeader('Authorization', this.sessaoService.token);
    }

    http.onreadystatechange = () => {
      if (http.readyState === 4) {
        this.loadingService.hide();
        if (http.status === HttpStatusCode.UNAUTHORIZED || http.status === HttpStatusCode.FORBIDDEN) {
          this.mensagemErroService
            .apresentaMensagens([new Mensagem('Você não tem permissão para exportar esse relatório', TipoMensagem.DANGER)]);
        } else if (http.status === HttpStatusCode.OK) {
          let nomeArquivo = http.getResponseHeader(Header.CONTENT_DISPOSITION)
            .replace('attachment; filename="', '').replace('"', "");
          this.httpUtilService.realizaDownload(http.response, nomeArquivo)
        } else {
          this.mensagemErroService.apresentaMensagens([new Mensagem('Erro interno, contate o administrador', TipoMensagem.DANGER)]);
        }
      }
    };
    http.send();
  }

  private criaUrl(formato: string) {
    let url = `${environment.backendUrl}${this.baseUrl}?`;
    if (this.parametros) {
      for (let [key, value] of this.parametros) {
        url += `${key}=${value}&`
      }
    }
    url += `formato=${formato}`;
    return url;
  }
}
