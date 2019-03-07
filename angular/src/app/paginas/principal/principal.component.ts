import { Component, OnInit } from '@angular/core';
import { AlertService } from '../../providers/alert.service';
import { Alert } from '../../models/alert';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss']
})
export class PrincipalComponent implements OnInit {

  constructor(public alertService: AlertService) { 
    this.alertService.alert = new Alert('');
  }

  ngOnInit() {
  }

}
