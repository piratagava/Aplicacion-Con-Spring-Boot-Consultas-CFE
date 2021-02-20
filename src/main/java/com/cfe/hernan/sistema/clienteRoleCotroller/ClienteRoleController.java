package com.cfe.hernan.sistema.clienteRoleCotroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cfe.hernan.sistema.exceptionsClienteRole.ClienteRoleExceptions;
import com.cfe.hernan.sistema.model.ClienteRole;
import com.cfe.hernan.sistema.service.ClienteRoleService;

@Controller
public class ClienteRoleController {
	private static final Logger log = Logger.getLogger(ClienteRoleController.class);

	@Autowired
	private ClienteRoleService clienteRoleService;

	private static final String ASOCIAR_CLIENTE_ROLE = "/insertarClienteRole";
	private static final String LISTAR_CLIENTE_ROLE = "/listarClienteRole";
	private static final String ACTUALIZAR_CLIENTE_ROLE = "/actualizarClienteRole";

	@PostMapping(value = ASOCIAR_CLIENTE_ROLE)
	public @ResponseBody Boolean insertarClienteRole(@RequestBody ClienteRole clienteRole)
			throws ClienteRoleExceptions {
		StringBuilder mensaje = new StringBuilder();
		try {
			System.out.println("LLEVO EL DATO DE cliente CUAL ES QUE TRAIGO SERIAIZADO" + clienteRole);
			clienteRoleService.guardarClienteRole(clienteRole);
			return true;
		} catch (ClienteRoleExceptions e) {
			mensaje.append("NO SE PUDO GUARDAR CLIENTE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR CLIENTE");
			return false;
		}
	}

	@GetMapping(value = LISTAR_CLIENTE_ROLE)
	public @ResponseBody List<Object> getClienteRole() throws ClienteRoleExceptions {
		StringBuilder mensaje = new StringBuilder();
		List<Object> listar = clienteRoleService.getclienteRole();
		try {
			return listar;
		} catch (Exception e) {
			mensaje.append("NO SE PUDO LISTAR CLIENTE_ROLE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE CONTROLLER CLIENTE_ROLE");
			return null;
		}

	}

	@PutMapping(value = ACTUALIZAR_CLIENTE_ROLE)
	public @ResponseBody Boolean actualizarClienteRole(@RequestBody ClienteRole clienteRole)
			throws ClienteRoleExceptions {
		StringBuilder mensaje = new StringBuilder();
		try {
			clienteRoleService.actualizarClienteRole(clienteRole.getName(), clienteRole.getId_cliente());
			return true;
		} catch (ClienteRoleExceptions e) {
			mensaje.append("NO SE PUDO ACTUALIZAR CLIENTE_ROLE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR CLIENTE ROLE");
			return false;
		}
	}
}
