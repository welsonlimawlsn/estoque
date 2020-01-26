import { Component, OnInit } from '@angular/core';
import { MensagemErroService } from './mensagem-erro.service';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-mensagem-erro',
  templateUrl: './mensagem-erro.component.html',
  styleUrls: ['./mensagem-erro.component.scss']
})
export class MensagemErroComponent implements OnInit {

  mensagens: string[] = [];

  constructor(private mensagemErroService: MensagemErroService) {
  }

  ngOnInit() {
    this.mensagemErroService.emmiter.subscribe(this.getNext());
  }

  private getNext() {
    return mensagens => this.mensagens = mensagens;
  }
}
