package com.ipartek.formacion.modelo.pojo;

public class Vehiculo {
	private int id;
	private String matricula;
	private String modelo;
	private Integer km;
	
	public Vehiculo(int id, String matricula, String modelo, Integer km) {
		this();
		setId(id);
		setMatricula(matricula);
		setModelo(modelo);
		setKm(km);
	}

	public Vehiculo() {
		super();
		this.id=-1;
		this.matricula="";
		this.modelo="";
		this.km=0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", km=" + km + "]";
	}
	
	

}
