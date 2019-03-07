import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Multa } from '../models/multa';
import { GLOBAL } from '../global';

@Injectable({
  providedIn: 'root'
})
export class MultaService {
  constructor(private http: HttpClient) { 
    console.trace('MultaService constructor');
  }

  update(accion: string, multa: Multa): Observable<any> {
    let uri = `${GLOBAL.endpoint}/wsrest-hector/api/agente/multa/${multa.id}/${accion}`;
    let body;
    if(accion == 'anular') {
      body = {
        "concepto": multa.concepto,
        "importe": multa.importe,
        "vehiculo": {
          "matricula": multa.vehiculo.matricula,
          "modelo": multa.vehiculo.modelo,
          "km": multa.vehiculo.km
        },
        "fechaBaja": new Date()
      }
    }
    else {
      body = {
        "concepto": multa.concepto,
        "importe": multa.importe,
        "vehiculo": {
          "matricula": multa.vehiculo.matricula,
          "modelo": multa.vehiculo.modelo,
          "km": multa.vehiculo.km
        },
        "fechaBaja": undefined,
        "fechaModificacion": new Date
      }     
    }

    return this.http.patch(uri, body);
  }

  multar(multa: Multa): Observable<any> {
    console.trace('multar');
    let uri = `${GLOBAL.endpoint}/wsrest-hector/api/agente/multa`;
    console.trace('AutorizacionService loggin uri: ' + uri);
    let body = {
      "concepto": multa.concepto,
      "importe": multa.importe,
      "agente": {"id": multa.agente.id },
      "vehiculo": {"id": multa.vehiculo.id}
    }
    return this.http.post(uri, body);
  }
}
