package com.cfe.hernan.sistema.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "cliente")
public class Cliente  implements Serializable {

	// @JsonIgnore elimina la propiedad completamente para no ver en el front
	@Id
	@Column(name = "id_cliente")
	// crea una tabla para guardar la secuencia de mi cliente
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	private Long id_cliente;

	@NotNull(message = "Nombre no debe ir null")
	@Column(name = "username", length = 45, unique = true)
	private String username;

	@NotNull(message = "Apellido Paterno no debe ir null")
	@Column(name = "apellido_paterno")
	private String apellido_paterno;

	@NotNull(message = "Apellido Materno no debe ir null")
	@Column(name = "apellido_materno")
	private String apellido_materno;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "Activo no debe ir null")
	@Column(name = "activo")
	private boolean activo;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "Password no debe ir null")
	@Column(name = "password")
	private String password;

	// esto es para permisos cuando inicie sesion Relacion con Role
	@ManyToMany
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente") }, inverseJoinColumns = {
					@JoinColumn(name = "name", referencedColumnName = "name") })
	@BatchSize(size = 20)
	private Set<Role> authorities = new HashSet<>();

	public Cliente() {
		Cliente cliente;
	}

	public Cliente(Long id_cliente, @NotNull(message = "Nombre no debe ir null") String username, @NotNull(message = "Apellido Paterno no debe ir null") String apellido_paterno, @NotNull(message = "Apellido Materno no debe ir null") String apellido_materno, @NotNull(message = "Activo no debe ir null") boolean activo, @NotNull(message = "Password no debe ir null") String password, Set<Role> authorities) {
		this.id_cliente = id_cliente;
		this.username = username;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.activo = activo;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cliente cliente = (Cliente) o;
		return id_cliente.equals(cliente.id_cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_cliente);
	}

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", username=" + username + ", apellido_paterno=" + apellido_paterno
				+ ", apellido_materno=" + apellido_materno + ", activo=" + activo + ", password=" + password
				+ ", authorities=" + authorities + "]";
	}

}
