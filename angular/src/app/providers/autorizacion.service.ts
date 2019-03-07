import { Injectable } from '@angular/core';
import { Agente } from '../models/agente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GLOBAL } from '../globales';

@Injectable({
  providedIn: 'root'
})
export class AutorizacionService {
  private storage = window.sessionStorage;
  private _agente: Agente;

  public get agente(): Agente {
    return this._agente;
  }
  public set agente(value: Agente) {
    this._agente = value;
  }

  public isLogged(): boolean {
    if (this.storage.getItem('isLogged') === "true") {
      return true;
    } else {
      return false;
    }
  }

  public setLogged(value: boolean) {
    console.debug('Hacemos setter de _isLogged y guardar en sessionStorage %o', this.storage);
    this.storage.setItem('isLogged', value.toString());
  }

  constructor(private http: HttpClient) {
    console.trace('AutorizacionService constructor');
    this._agente = new Agente(-1, undefined, undefined, undefined);
  }

  public saveAgente(agente: Agente) {
    agente.password = '';
    this.storage.setItem('agente', JSON.stringify(agente));
  }

  public getAgente(): any {

    let agenteString = this.storage.getItem('agente');
    if (agenteString) {
      return JSON.parse(agenteString);
    } else {
      return undefined;
    }
  }
  
  /**
   * metodo para llamar al servicio rest del backoffice
   * @param placa 
   * @param password 
   */
  login(placa: string, password: string): Observable<any> {
    let uri = `${GLOBAL.endpoint}/wsrest-hector/api/agente/login/${placa}/${password}`;
    console.trace('AutorizacionService loggin uri: ' + uri);
    return this.http.get(uri);
  }

  logout() {
    console.debug('Hacemos setter de _isLogged a false %o', this.storage);
    this.storage.removeItem('isLogged');
  }
}
