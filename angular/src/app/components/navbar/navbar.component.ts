import { Component, OnInit } from '@angular/core';
import { AutorizacionService } from '../../providers/autorizacion.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(public autorizacionService: AutorizacionService, private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.autorizacionService.logout();
    this.router.navigate(['/login']);
  }
}
