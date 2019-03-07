import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Vehiculo } from '../../models/vehiculo';
import { AutorizacionService } from '../../providers/autorizacion.service';
import { VehiculoService } from '../../providers/vehiculo.service';
import { Multa } from '../../models/multa';
import { MultaService } from '../../providers/multa.service';
import { Router } from '@angular/router';
import { Alert } from '../../models/alert';
import { AlertService } from '../../providers/alert.service';

@Component({
  selector: 'app-multar',
  templateUrl: './multar.component.html',
  styleUrls: ['./multar.component.scss']
})
export class MultarComponent implements OnInit {

  vehiculo: Vehiculo;
  formulario: FormGroup;
  alert: Alert;
  
  constructor(private formBuilder: FormBuilder,
    private vehiculoService: VehiculoService, 
    private multaService: MultaService, 
    private autorizacionService: AutorizacionService,
    private alertService: AlertService,
    private router: Router) { 

    this.crearFormulario();
    this.vehiculo = this.vehiculoService.vehiculo;
    if(this.vehiculo == undefined) {
      this.router.navigate(['/buscar-vehiculo']);
    }
  }

  crearFormulario() {
    this.formulario = this.formBuilder.group({
      concepto: [
        '', // Value
        [Validators.required, Validators.minLength(10)] // Validaciones
      ],
      importe: [
        undefined, // Value
        [Validators.required, Validators.min(10)] // Validaciones
      ]
    });
  }

  multar() {
    let multa = new Multa(this.formulario.value.concepto, this.formulario.value.importe, undefined, this.autorizacionService.getAgente(), this.vehiculo)
    this.multaService.multar(multa).subscribe(
      resultado => {
        console.log('Multa registrada con éxito %o', resultado);
        this.alert = new Alert('Multa al coche con matrícula '+this.vehiculo.matricula+' registrada con éxito', Alert.SUCCESS)
        this.alertService.alert = this.alert;
        this.router.navigate(['/ver-multas/activas']);
      },
      error => {
        this.alert = new Alert('No se pudo multar al coche con matrícula '+this.vehiculo.matricula, Alert.DANGER)
        this.alertService.alert = this.alert;
        console.error(error);
      }
    );
  }

  ngOnInit() {
  }

}
