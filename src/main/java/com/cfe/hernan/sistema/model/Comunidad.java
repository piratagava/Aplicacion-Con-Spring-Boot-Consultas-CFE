package com.cfe.hernan.sistema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comunidad")
public class Comunidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_comunidad;

	@Column(name = "direccion")
	@NotNull(message = "Campo direccion no debe ir vacio a la base")
	private String direccion;

	public Comunidad() {
		Comunidad comunidad;
	}

	public Comunidad(int id_comunidad, String direccion) {
		super();
		this.id_comunidad = id_comunidad;
		this.direccion = direccion;
	}

	public int getId_comunidad() {
		return id_comunidad;
	}

	public void setId_comunidad(int id_comunidad) {
		this.id_comunidad = id_comunidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Comunidad [id_comunidad=" + id_comunidad + ", direccion=" + direccion + "]";
	}

}
