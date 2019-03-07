import { Agente } from './agente';
import { Vehiculo } from './vehiculo';

export class Multa {
    private _id: number;
    private _concepto: string;
    private _importe: number;
    private _fechaAlta: string;
    private _fechaModificacion: string;
    private _fechaBaja: string;
    private _agente: Agente;
    private _vehiculo: Vehiculo;

    constructor( 
        concepto: string, 
        importe: number, 
        fechaAlta: string, 
        agente: Agente,
        vehiculo: Vehiculo,
        id?: number,
        fechaModificacion?: string,
        fechaBaja?: string) {

        this._id = (id) ? id : -1;
        this._concepto = concepto;
        this._importe = importe;
        this._fechaAlta = fechaAlta;
        this._fechaModificacion = (fechaModificacion) ? fechaModificacion : undefined;
        this._fechaBaja = (fechaBaja) ? fechaBaja : undefined;
        this._agente = agente;
        this._vehiculo = vehiculo;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get concepto(): string {
        return this._concepto;
    }
    public set concepto(value: string) {
        this._concepto = value;
    }

    public get importe(): number {
        return this._importe;
    }
    public set importe(value: number) {
        this._importe = value;
    }

    public get fechaAlta(): string {
        return this._fechaAlta;
    }
    public set fechaAlta(value: string) {
        this._fechaAlta = value;
    }

    public get fechaModificacion(): string {
        return this._fechaModificacion;
    }
    public set fechaModificacion(value: string) {
        this._fechaModificacion = value;
    }

    public get fechaBaja(): string {
        return this._fechaBaja;
    }
    public set fechaBaja(value: string) {
        this._fechaBaja = value;
    }

    public get agente(): Agente {
        return this._agente;
    }
    public set agente(value: Agente) {
        this._agente = value;
    }

    public get vehiculo(): Vehiculo {
        return this._vehiculo;
    }
    public set vehiculo(value: Vehiculo) {
        this._vehiculo = value;
    }
}
