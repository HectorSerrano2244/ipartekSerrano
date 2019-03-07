import { Component, OnInit } from '@angular/core';
import { Multa } from '../../models/multa';
import { AgenteService } from '../../providers/agente.service';
import { AutorizacionService } from '../../providers/autorizacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MultaService } from 'src/app/providers/multa.service';
import { Alert } from '../../models/alert';
import { AlertService } from '../../providers/alert.service';

@Component({
  selector: 'app-ver-multas',
  templateUrl: './ver-multas.component.html',
  styleUrls: ['./ver-multas.component.scss']
})
export class VerMultasComponent implements OnInit {
  multas: Multa[];
  multa: Multa;
  estado: string;
 
 alert: Alert;
  constructor(
    private agenteService: AgenteService, 
    private autorizacionService: AutorizacionService,
    private multasService: MultaService,
    private route: ActivatedRoute,
    private alertService: AlertService,
    private router: Router) { 
    
    this.multas = [];
    this.estado = this.route.snapshot.params['estado'];
    this.listarMultas();
    this.alert = this.alertService.alert;
  }

  ngOnInit() {
    console.trace('VerMultasComponent ngOnInit');
  }

  update(accion: string, multa: Multa) {
    console.trace('Método update');
    let confirmacion = false;
    if (accion == 'recuperar') {
      confirmacion = confirm('¿Desea recuperar esta multa?');
    }
    else {
      confirmacion = confirm('¿Desea anular esta multa?');
    }
    if(confirmacion) {
      this.multasService.update(accion, multa).subscribe(
        resultado => {
          console.debug('Multa modificada con éxito %o', resultado);
          if(accion == 'recuperar') {
            this.estado = 'activas';
            this.alert = new Alert('Multa activada con éxito', Alert.SUCCESS);
            this.router.navigate(['/ver-multas/activas']);
          }
          else {
            this.estado = 'inactivas';
            this.alert = new Alert('Multa desactivada con éxito', Alert.SUCCESS);
            this.router.navigate(['/ver-multas/inactivas']);
          }
          this.listarMultas();
        },
        error => {
          if(accion == 'recuperar') {
            this.alert = new Alert('No se pudo activar la multa', Alert.DANGER);
          }
          else {
            this.alert = new Alert('No se pudo desactivar la multa', Alert.DANGER);
          }
          console.error('peticion incorrecta %o', error);
        }
    );//subscribe
    }

  }
  
  listarMultas() {
    console.trace('Método listarMultas');
    this.agenteService.getMultasByAgente(this.autorizacionService.getAgente().id, this.estado).subscribe(
      resultado => {
        console.debug('peticion correcta %o', resultado);
        this.multas = resultado;
        if (resultado == '') {
          if (this.estado == 'activas') {
            this.alert = new Alert('No existen multas', Alert.WARNING);
          }
          else {
            this.alert = new Alert('No existen multas anuladas', Alert.WARNING);
          }
        }
      },
      error => {
        console.error('peticion incorrecta %o', error);
        this.alert = new Alert('Lo sentimos, no se pudo cargar la lista', Alert.DANGER);
      }
    );//subscribe
  }

  
}
