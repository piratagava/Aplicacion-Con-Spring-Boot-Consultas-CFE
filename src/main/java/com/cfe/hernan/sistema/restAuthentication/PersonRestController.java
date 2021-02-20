package com.cfe.hernan.sistema.restAuthentication;

import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.service.ClienteSecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonRestController {

	private final ClienteSecurityService clienteService;

	public PersonRestController(ClienteSecurityService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/api/cliente")
	public ResponseEntity<Cliente> getActualCliente(){
		return ResponseEntity.ok(clienteService.getUserWithAuthorities().get());
	}

}
