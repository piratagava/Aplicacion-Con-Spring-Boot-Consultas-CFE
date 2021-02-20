package com.cfe.hernan.sistema.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.repository.ClienteRepository;
import com.cfe.hernan.sistema.security.SecurityUtils;

@Service
@Transactional
public class ClienteSecurityService {

	private final ClienteRepository clienteRepository;

	public ClienteSecurityService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	   @Transactional(readOnly = true)
	   public Optional<Cliente> getUserWithAuthorities() {
	      return SecurityUtils.getCurrentUsername().flatMap(clienteRepository::findOneWithAuthoritiesByUsername);
	   }
	//.flatMap(), proyecta una lista de elementos de cada elemento original y los concatena en un único stream.
	//.flatmap produce de cero a n valores de salida por cada valor de entrada
}
