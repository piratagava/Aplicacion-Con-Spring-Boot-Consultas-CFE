package com.cfe.hernan.sistema.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;


//@Embeddable
public class ClienteRolePK implements Serializable {

	private static final long serialVersionUID = 6342586275951743721L;

	@Column(name = "id_cliente")
	private int id_cliente;

	@Column(name = "name")
	private String name;

	public ClienteRolePK() {
		ClienteRolePK clienteRolePK;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + Objects.hashCode(this.id_cliente);
		hash = 31 * hash + Objects.hashCode(this.name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ClienteRolePK other = (ClienteRolePK) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		if (!Objects.equals(this.id_cliente, other.id_cliente)) {
			return false;
		}
		return true;
	}

	public ClienteRolePK(int id_cliente, String name) {
		super();
		this.id_cliente = id_cliente;
		this.name = name;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getRole() {
		return name;
	}

	public void setRole(String name) {
		this.name = name;
	}

}
