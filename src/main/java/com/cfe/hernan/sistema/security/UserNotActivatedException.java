package com.cfe.hernan.sistema.security;

import org.springframework.security.core.AuthenticationException;

public class UserNotActivatedException extends AuthenticationException {
	/**
	 * Esta excepción se lanza en caso de que un usuario no activado intente
	 * autenticarse.
	 */
	private static final long serialVersionUID = -1126699074574529145L;

	public UserNotActivatedException(String message) {
		super(message);
	}
}
