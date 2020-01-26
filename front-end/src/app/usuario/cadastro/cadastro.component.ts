import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AcaoService } from '../../acao.service';
import { UsuarioService } from '../usuario.service';
import { Grupo } from '../../grupo/model/grupo';
import { GrupoService } from '../../grupo/grupo.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  form: FormGroup;

  grupos: Grupo[];

  constructor(
    private fb: FormBuilder,
    private acaoService: AcaoService,
    private usuarioService: UsuarioService,
    private grupoService: GrupoService
  ) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      usuario: ['', Validators.required],
      senha: ['', Validators.required],
      codigoGrupo: ['', Validators.required]
    });

    this.acaoService.executa(
      this.grupoService.listaGrupos(), false
    ).subscribe(resposta => this.grupos = resposta.grupos);
  }

  cadastra() {
    this.acaoService.executa(
      this.usuarioService.cadastraCliente(this.form.value)
    ).subscribe();
  }
}
