package com.cfe.hernan.sistema.service.implement;

import com.cfe.hernan.sistema.exceptionsCliente.ClienteException;
import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.repository.ClienteRepository;
import com.cfe.hernan.sistema.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplement implements ClienteService {
	private static final Logger log = Logger.getLogger(ClienteServiceImplement.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void guardarCliente(Cliente cliente) throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (cliente.getUsername().equals(null) || cliente.getApellido_paterno().equals(null)
					|| cliente.getApellido_materno().equals(null) || cliente.getPassword().equals(null)) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL");
				throw new ClienteException("EL DATO NULL ES NOMBRE : " + cliente.getUsername() + " O ES :"
						+ cliente.getApellido_paterno() + " O ES :" + cliente.getApellido_materno() + " O ES :"
						+ cliente.isActivo() + " O ES :" + cliente.getPassword());
			} else {
				clienteRepository.save(cliente);
			}
		} catch (ClienteException e) {
			mensaje.append("NO SE PUDO GUARDAR CLIENTE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR CLIENTE");
			throw new ClienteException(e.getMessage());
		}
	}

	@Override
	public List<Cliente> listarClientes() throws ClienteException {
		return clienteRepository.findAll();
	}

	@Override
	public void deleteCliente(Cliente cliente) throws ClienteException {
		clienteRepository.delete(cliente);
	}

	@Override
	public Integer consultaUltimoIdClienteRegistrado() throws ClienteException {
		return clienteRepository.consultaIdCliente();
	}

	@Override
	public void actualizarCliente(String nombre, String apellidoP, String apellidoM, Boolean activo, String password,
			Long id_cliente) throws ClienteException {
		clienteRepository.actualizarCliente(nombre, apellidoP, apellidoM, activo, password, id_cliente);

	}

	@Override
	public List<Cliente> clienteNoAsociadosDetalleService() throws ClienteException {
		return clienteRepository.consultaClienteNoAsociadosDetalleService();

	}

}
