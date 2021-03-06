package com.ipartek.formacion.modelo.pojo;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Multa {
	private int id;
	@NotNull
	@Digits(fraction = 2, integer = 5)
	private Double importe;
	@NotNull
	@Size(min=10, max=255)
	private String concepto;
	private Date fechaAlta;
	private Date fechaModificacion;
	private Date fechaBaja;
	private Agente agente;
	private Vehiculo vehiculo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public Multa(int id, Double importe, String concepto, Date fechaAlta, Date fechaModificacion, Date fechaBaja, Agente agente, Vehiculo vehiculo) {
		this();
		setId(id);
		setImporte(importe);
		setConcepto(concepto);
		setFechaAlta(fechaAlta);
		setFechaModificacion(fechaModificacion);
		setFechaBaja(fechaBaja);
		setAgente(agente);
		setVehiculo(vehiculo);
	}
	
	public Multa() {
		super();
		this.id=-1;
		this.importe=0.0;
		this.concepto="";
		this.fechaAlta=null;
		this.fechaModificacion=null; 
		this.fechaBaja=null; 
		this.agente=new Agente();
		this.vehiculo=new Vehiculo();
	}
	
	@Override
	public String toString() {
		return "Multa [id=" + id + ", importe=" + importe + ", concepto=" + concepto + ", fechaAlta=" + fechaAlta + ", agente="
				+ agente + ", vehiculo=" + vehiculo + "]";
	}
	
	
	
	


}
