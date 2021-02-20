package com.cfe.hernan.sistema.security;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cfe.hernan.sistema.model.Cliente;
import com.cfe.hernan.sistema.repository.ClienteRepository;

@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {
	private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

	private final ClienteRepository clienteRepository;

	public UserModelDetailsService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		log.debug("Autenticado cliente '{}'", login);

		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		return clienteRepository.findOneWithAuthoritiesByUsername(lowercaseLogin)
				.map(user -> createSpringSecurityUser(lowercaseLogin, user))
				.orElseThrow(() -> new UsernameNotFoundException(
						"Cliente" + lowercaseLogin + "No se encontrol el cliente en la Base de datos"));
	}

	 private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, Cliente  cliente) {
		if (!cliente.isActivo()) {
			throw new UserNotActivatedException("User" + lowercaseLogin + "No esta activado");
		}
		List<GrantedAuthority> grantedAuthorities = cliente.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toList());
				
		return new org.springframework.security.core.userdetails.User(cliente.getUsername(),
				cliente.getPassword(), grantedAuthorities);
	}

}
