package com.cfe.hernan.sistema.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "authority")
public class Role {

	@Id
	@Column(name = "name", length = 40)
	@NotNull
	private String name;

	@Column(name = "id_role")
	@NotNull
	private int id_role;

	public Role() {
		Role role;
	}

	public Role(@NotNull String name, @NotNull int id_role) {
		super();
		this.name = name;
		this.id_role = id_role;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Role authority = (Role) obj;
		return name == authority.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Role [role=" + name + "]";
	}

}
