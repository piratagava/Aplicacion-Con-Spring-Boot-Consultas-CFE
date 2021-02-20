package com.cfe.hernan.sistema.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cfe.hernan.sistema.exceptionsClienteRole.ClienteRoleExceptions;
import com.cfe.hernan.sistema.model.ClienteRole;

public interface ClienteRoleService {

	public void guardarClienteRole(ClienteRole clienteRole) throws ClienteRoleExceptions;

	public List<Object> getclienteRole() throws ClienteRoleExceptions;

	public void actualizarClienteRole(String role, int id_cliente) throws ClienteRoleExceptions;

	public void eliminarClienteRole(int id_cliente) throws ClienteRoleExceptions;
}
