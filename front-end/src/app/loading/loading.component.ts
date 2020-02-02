import { Component, OnInit } from '@angular/core';
import { LoadingService } from './loading.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit {

  showing = false;

  constructor(private loadingService: LoadingService) {
  }

  ngOnInit() {
    this.loadingService.emitter.subscribe(value => {
      this.showing = value;
    });
  }

}
