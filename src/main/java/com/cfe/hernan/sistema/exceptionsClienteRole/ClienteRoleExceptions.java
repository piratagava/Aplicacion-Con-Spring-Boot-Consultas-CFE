package com.cfe.hernan.sistema.exceptionsClienteRole;

import org.apache.commons.lang.exception.NestableException;

public class ClienteRoleExceptions extends NestableException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteRoleExceptions() {
		super();
	}

	public ClienteRoleExceptions(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ClienteRoleExceptions(String msg) {
		super(msg);
	}

	public ClienteRoleExceptions(Throwable cause) {
		super(cause);
	}
}
