import { Component, OnInit } from '@angular/core';
import { VehiculoService } from '../../providers/vehiculo.service';
import { Vehiculo } from '../../models/vehiculo';
import { AutorizacionService } from '../../providers/autorizacion.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Alert } from '../../models/alert';

@Component({
  selector: 'app-buscar-vehiculo',
  templateUrl: './buscar-vehiculo.component.html',
  styleUrls: ['./buscar-vehiculo.component.scss']
})
export class BuscarVehiculoComponent implements OnInit {

  vehiculo: Vehiculo;
  formulario: FormGroup;
  alert: Alert;

  constructor(private formBuilder: FormBuilder, 
    public vehiculoService: VehiculoService, 
    private autorizacionService: AutorizacionService,
    private router: Router) { 
      
    console.trace('BuscarVehiculoComponent constructor');
    this.crearFormulario();
    this.alert = new Alert('');
  }

  ngOnInit() {
    console.trace('BuscarVehiculoComponent ngOnInit');
  }

  irAMultar() {
    console.trace('irAMultar');
    this.vehiculoService.getByMatricula(this.formulario.controls['matricula'].value).subscribe(
      data => {
        console.debug('peticion correcta %o', data);
        this.vehiculoService.vehiculo = data;
        this.router.navigate(['/multar']);
      },
      error => {
        this.alert = new Alert('No existe el coche con la matricula '+this.formulario.controls['matricula'].value, Alert.WARNING);
        console.error('peticion incorrecta %o', error);
      }
    );//subscribe
  }

  crearFormulario() {
    this.formulario = this.formBuilder.group({
      matricula: [
        '', // Value
        [Validators.required, Validators.pattern('\\d{4}[a-zA-Z]{3}')] // Validaciones
      ]
    });
  }

}
