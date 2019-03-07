import { Injectable } from '@angular/core';
import { Alert } from '../models/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private _alert: Alert;

  public get alert(): Alert {
    return this._alert;
  }
  public set alert(value: Alert) {
    this._alert = value;
  }

  constructor() { }
}
