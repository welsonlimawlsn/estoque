import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '../usuario.service';
import { AcaoService } from '../../acao.service';
import { SessaoService } from '../../sessao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private acaoService: AcaoService,
    private sessaoService: SessaoService
  ) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      usuario: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  login() {
    this.acaoService.executa(
      this.usuarioService.loginCliente(this.form.value)
    ).subscribe(resposta => this.sessaoService.iniciaSessao(resposta));
  }

}
