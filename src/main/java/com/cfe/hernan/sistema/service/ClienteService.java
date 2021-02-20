package com.cfe.hernan.sistema.service;

import java.util.List;
import java.util.Optional;

import com.cfe.hernan.sistema.exceptionsCliente.ClienteException;
import com.cfe.hernan.sistema.exceptionsRole.RoleException;
import com.cfe.hernan.sistema.model.Cliente;

public interface ClienteService {

	public void guardarCliente(Cliente cliente) throws ClienteException;

	public List<Cliente> listarClientes() throws ClienteException;

	public void deleteCliente(Cliente cliente) throws ClienteException;

	public Integer consultaUltimoIdClienteRegistrado() throws ClienteException;

	public void actualizarCliente(String nombre, String apellidoP, String apellidoM, Boolean activo, String password,
			Long id_cliente) throws ClienteException;
	
	 public List<Cliente> clienteNoAsociadosDetalleService() throws ClienteException ;
}
