package com.cfe.hernan.sistema.clienteController;

import com.cfe.hernan.sistema.exceptionsCliente.ClienteException;
import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.service.ClienteService;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClienteController {
	private static final Logger log = Logger.getLogger(ClienteController.class);
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String CLIENTE_INSERT_DATA = "/insertarCliente";
	private static final String LISTA_ALL_CLIENTE = "/listaCliente";
	private static final String UPDATE_CLIENTE = "/actualizarCliente";
	private static final String DELETE_CLIENTE = "/eliminarCliente";
	private static final String CONSULTA_ULTIMO_ID_CLIENTE_REGISTRADO = "/consultaIdClienteReciente";
	private static final String CONSULTA_CLIENTE_LOGADO = "/consultaClienteSesion";
	private static final String CONSULTA_CLIENTE_EN_DETALLE_SERVICIO = "/consultaEstaClienteEnDetalleServicio";

	@GetMapping(value = CONSULTA_ULTIMO_ID_CLIENTE_REGISTRADO)
	public @ResponseBody Integer consultaIdCliente() throws ClienteException {
		try {
			return clienteService.consultaUltimoIdClienteRegistrado();
		} catch (ClienteException e) {
			return 0;
		}
	}

	@PostMapping(value = CLIENTE_INSERT_DATA)
	public @ResponseBody Boolean insertarCliente(@RequestBody Cliente cliente) {
		System.out.println("CLIENT ES EL SIGUIENTE DESDE EL FRNT TRA ESTO " + cliente);
		try {
			cliente.setId_cliente(cliente.getId_cliente());
			cliente.setUsername(cliente.getUsername());
			cliente.setApellido_paterno(cliente.getApellido_paterno());
			cliente.setApellido_materno(cliente.getApellido_materno());
			cliente.setActivo(cliente.isActivo());
			cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
			cliente.setAuthorities(cliente.getAuthorities());
			clienteService.guardarCliente(cliente);
			return true;
		} catch (ClienteException e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@GetMapping(value = LISTA_ALL_CLIENTE)
	public @ResponseBody List<Cliente> listarClientes() throws ClienteException {
		try {
			return clienteService.listarClientes();
		} catch (ClienteException e) {
			System.out.print("ERROR EN LISTAR POR " + e.toString());
			System.out.print("MENSAJE DE ERROR INTERNO NO SE PUDO LISTAR LOS PRODUCTOS " + e.getMessage());
			return null;
		}
	}

		@GetMapping(value = CONSULTA_CLIENTE_EN_DETALLE_SERVICIO)
		public @ResponseBody List<Cliente> listarClientesSinoExistenEnDetalleServicio() throws ClienteException {
			try {
				return clienteService.clienteNoAsociadosDetalleService();
			} catch (ClienteException e) {
				System.out.print("ERROR EN LISTAR POR " + e.toString());
				System.out.print("MENSAJE DE ERROR INTERNO NO SE PUDO LISTAR LOS PRODUCTOS " + e.getMessage());
				return null;
			}

		}

	@DeleteMapping(value = DELETE_CLIENTE)
	public @ResponseBody Boolean deleteProducto(@RequestBody Cliente cliente) throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			clienteService.deleteCliente(cliente);
			return true;
		} catch (ClienteException ex) {
			mensaje.append("NO SE PUDO ELIMINAR CLIENTE ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE BORRAR CLIENTE CONTROLLER");
			throw new ClienteException(ex.getMessage());
		}
	}

	@PutMapping(value = UPDATE_CLIENTE)
	public @ResponseBody Boolean updateCliente(@RequestBody Cliente cliente) throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			clienteService.actualizarCliente(cliente.getUsername(), cliente.getApellido_paterno(),
					cliente.getApellido_materno(), cliente.isActivo(), passwordEncoder.encode(cliente.getPassword()),
					cliente.getId_cliente());
			return true;
		} catch (ClienteException e) {
			mensaje.append("NO SE PUDO ACTUALIZAR CLIENTE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR CLIENTE CONTROLLER");
			throw new ClienteException(e.getMessage());
		}

	}

}
