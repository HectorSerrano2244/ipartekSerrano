import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehiculo } from '../models/vehiculo';
import { GLOBAL } from '../global';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {
  private _vehiculo: Vehiculo;

  public get vehiculo(): Vehiculo {
    return this._vehiculo;
  }
  public set vehiculo(value: Vehiculo) {
    this._vehiculo = value;
  }

  constructor(private http: HttpClient) {
    console.trace('VehiculoService constructor');
  }

  getByMatricula(matricula: string): Observable<any> {
    let uri = `${GLOBAL.endpoint}/wsrest-hector/api/vehiculo/${matricula}`;
    console.trace('getByMatricula '+uri);
    return this.http.get(uri);
  }
}
