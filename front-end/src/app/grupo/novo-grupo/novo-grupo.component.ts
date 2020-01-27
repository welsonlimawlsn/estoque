import { Component, OnInit } from '@angular/core';
import { Opcao } from '../../formulario/multi-select/multi-select.component';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FuncionalidadeService } from '../../funcionalidade/funcionalidade.service';
import { AcaoService } from '../../acao.service';
import { GrupoService } from '../grupo.service';

@Component({
  selector: 'app-novo-grupo',
  templateUrl: './novo-grupo.component.html',
  styleUrls: ['./novo-grupo.component.scss']
})
export class NovoGrupoComponent implements OnInit {
  opcoes: Opcao[];

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private funcionalidadeService: FuncionalidadeService,
              private acaoService: AcaoService,
              private grupoService: GrupoService) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      funcionalidades: [[], Validators.required]
    });

    this.acaoService.executa(
      this.funcionalidadeService.listaFuncionalidades(), false
    ).subscribe(
      resposta => this.opcoes = resposta.funcionalidades
        .map(funcionalidade => new Opcao(funcionalidade.codigo, `${funcionalidade.nome} - ${funcionalidade.descricao}`)));
  }

  criaGrupo() {
    this.acaoService.executa(
      this.grupoService.novoGrupo(this.form.value)
    ).subscribe();
  }

}
