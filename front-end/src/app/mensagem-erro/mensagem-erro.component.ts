import { Component, OnInit } from '@angular/core';
import { Mensagem, MensagemErroService } from './mensagem-erro.service';

@Component({
  selector: 'app-mensagem-erro',
  templateUrl: './mensagem-erro.component.html',
  styleUrls: ['./mensagem-erro.component.scss']
})
export class MensagemErroComponent implements OnInit {

  mensagens: Mensagem[] = [];

  constructor(private mensagemErroService: MensagemErroService) {
  }

  ngOnInit() {
    this.mensagemErroService.emmiter.subscribe(mensagens => this.mensagens = mensagens);
  }

}
