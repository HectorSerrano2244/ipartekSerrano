import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GLOBAL } from '../globales';

@Injectable({
  providedIn: 'root'
})
export class AgenteService {

  constructor(private http: HttpClient) { }

  getMultasByAgente(idagente: number, estado: string): Observable<any> {

    let uri = `${GLOBAL.endpoint}/wsrest-hector/api/agente/${idagente}/multas/${estado}`;
    console.trace('getByMatricula ' + uri);
    return this.http.get(uri);
  }
}
