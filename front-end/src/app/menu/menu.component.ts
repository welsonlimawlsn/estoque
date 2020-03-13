import { Component, Input, OnInit } from '@angular/core';
import { Menu } from './model/menu';
import { SessaoService } from '../sessao.service';
import { Cliente } from '../usuario/model/cliente';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  @Input()
  menu: Menu[];
  cliente: Cliente;

  constructor(private sessaoService: SessaoService) {
  }

  ngOnInit() {
    this.preencheMenu();
    this.sessaoService.menuEvent.subscribe(menu => {
      this.preencheMenu();
    });
  }

  sair() {
    this.sessaoService.logout();
  }

  private preencheMenu() {
    this.menu = this.sessaoService.menu;
    this.cliente = this.sessaoService.cliente;
  }
}
