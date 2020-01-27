import { Component, OnInit } from '@angular/core';
import { SessaoService } from './sessao.service';
import { Menu } from './menu/model/menu';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  menuVisivel = false;

  menu: Menu[];

  constructor(private sessaoService: SessaoService) {
  }

  ngOnInit(): void {
    this.menuVisivel = this.sessaoService.isAutenticado();
    this.sessaoService.menuEvent.subscribe(menu => {
      if (menu) {
        this.menuVisivel = true;
        this.menu = menu;
      } else {
        this.menuVisivel = false;
        this.menu = undefined;
      }
    });
  }
}
