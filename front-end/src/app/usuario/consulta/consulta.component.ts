import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Validadores } from '../../core/validadores';
import { Option } from '../../formulario/select/select.component';
import { AcaoService } from '../../acao.service';
import { GrupoService } from '../../grupo/grupo.service';
import { ConsultaClientesResposta } from '../model/consulta-clientes-resposta';
import { UsuarioService } from '../usuario.service';
import { HttpUtilService } from "../../core/http-util.service";

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.scss']
})
export class ConsultaComponent implements OnInit {

  form: FormGroup;

  grupos: Option[];

  resposta: ConsultaClientesResposta;

  constructor(
    private fb: FormBuilder,
    private acaoService: AcaoService,
    private grupoService: GrupoService,
    private usuarioService: UsuarioService,
    private httpUtilService: HttpUtilService
  ) {
  }

  ngOnInit() {

    this.form = this.fb.group({
      nome: [''],
      cpf: ['', Validadores.cpf],
      grupo: ['']
    });

    this.acaoService.executa(
      this.grupoService.listaGrupos(), false
    ).subscribe(resposta => this.grupos = resposta.grupos.map(grupo => new Option(grupo.id, grupo.nome)));

    this.filtra();
  }

  filtra() {
    this.acaoService.executa(
      this.usuarioService.listaClientes({
        nomeCliente: this.form.value.nome,
        cpf: this.form.value.cpf,
        grupo: this.form.value.grupo
      }), false
    ).subscribe((resposta: ConsultaClientesResposta) => {
      this.resposta = resposta;
    });
  }

  downloadRelatorio() {
    this.acaoService.executa(
      this.usuarioService.relatorioConsultaCliente({
        nomeCliente: this.form.value.nome,
        cpf: this.form.value.cpf,
        grupo: this.form.value.grupo
      })
    ).subscribe(data => {
      this.httpUtilService.realizaDownload(data, 'Relatório Consulta Usuários');
    })
  }
}
