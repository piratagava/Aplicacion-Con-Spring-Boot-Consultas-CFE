package com.cfe.hernan.sistema.service.implement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cfe.hernan.sistema.exceptionsCliente.ClienteException;
import com.cfe.hernan.sistema.exceptionsClienteRole.ClienteRoleExceptions;
import com.cfe.hernan.sistema.model.ClienteRole;
import com.cfe.hernan.sistema.repository.ClienteRoleRepository;
import com.cfe.hernan.sistema.service.ClienteRoleService;

@Service
public class ClienteRoleServiceImplement implements ClienteRoleService {
	private static final Logger log = Logger.getLogger(ClienteRoleServiceImplement.class);
	
	@Autowired
	private ClienteRoleRepository clienteRoleRepository;

	@Override
	public void guardarClienteRole(ClienteRole clienteRole) throws ClienteRoleExceptions {	
		StringBuilder mensaje = new StringBuilder();
		try {
			if (clienteRole.getName().equals("") || clienteRole.getName().equals(null) || clienteRole.getName().equals("")) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL");
				throw new ClienteRoleExceptions("EL DATO NULL ES : "	+ clienteRole.getName());
			} else {
				clienteRoleRepository.save(clienteRole);
			}
		} catch (ClienteRoleExceptions e) {
			mensaje.append("NO SE PUDO GUARDAR CLIENTE ROLE ERROR EN EL CATCH").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR CLIENTE");
			throw new ClienteRoleExceptions(e.getMessage());
		}		
		// otra opcion si no llega a guardar
		// ClienteRole employee = new ClienteRole(new ClienteRolePK());
		// clienteRoleRepository.save(employee);
	}

	@Override
	public List<Object> getclienteRole() throws ClienteRoleExceptions {
		return clienteRoleRepository.aListOfClienteRole();
	}

	@Override
	public void actualizarClienteRole(String role, int id_cliente) throws ClienteRoleExceptions {
		clienteRoleRepository.actualizarClienteRole(role, id_cliente);
	}

	@Override
	public void eliminarClienteRole(int id_cliente) throws ClienteRoleExceptions {
		clienteRoleRepository.eliminarPorIdCliente(id_cliente);
	}

}
