package com.cfe.hernan.sistema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "user_authority")
@IdClass(ClienteRolePK.class)
public class ClienteRole {
	// LLaves compuestas
	// @EmbeddedId solo para referencia
	// private ClienteRolePK clienteRolePK;
	@Id
	@Column(name = "id_cliente")
	@GeneratedValue
	private int id_cliente;

	@Id
	@Column(name = "name")
	private String name;

	public ClienteRole() {
		ClienteRole clienteRole;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClienteRole [id_cliente=" + id_cliente + ", name=" + name + "]";
	}

}