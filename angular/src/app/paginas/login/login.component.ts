import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AutorizacionService } from '../../providers/autorizacion.service';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { Alert } from '../../models/alert';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  formulario: FormGroup;
  alert: Alert;

  constructor(
    private autorizacionService: AutorizacionService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    console.trace('LoginComponent constructor');
    this.crearFormulario();
    this.alert = new Alert('');
  }

  ngOnInit() {
    
  }

  crearFormulario() {
    this.formulario = this.formBuilder.group({
      placa: [
        undefined, // Value
        [Validators.required, Validators.min(6), Validators.pattern('\\d{6}')] // Validaciones
      ],
      password: [
        undefined,
        [Validators.required, Validators.minLength(6)]
      ],
    });
  }

  comprobar(){
    console.trace('click boton submit');
    let placa = this.formulario.controls.placa.value;
    let password = this.formulario.controls.password.value;
    console.debug('nombre: %s password: %s',placa , password);

    // llamar servicio Rest, realizar logica dentro de subscripcion
    // Cuidado es una llamada Asincrona
    this.autorizacionService.login(placa, password).subscribe(
      data =>{
        console.debug('Json Agente %o', data);
        this.autorizacionService.setLogged(true);
        this.autorizacionService.saveAgente(data);
        this.router.navigate(['/principal']);
      },
      error=>{
        console.warn('error login %o', error);
        this.alert = new Alert('ERROR: Credenciales incorrectas', Alert.WARNING);
        this.autorizacionService.setLogged(false);
      }
    );
  }
}