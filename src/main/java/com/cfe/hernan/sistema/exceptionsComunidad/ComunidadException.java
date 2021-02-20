package com.cfe.hernan.sistema.exceptionsComunidad;

import org.apache.commons.lang.exception.NestableException;

public class ComunidadException extends NestableException {
	private static final long serialVersionUID = 1L;

	public ComunidadException() {
		super();
	}

	public ComunidadException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ComunidadException(String msg) {
		super(msg);
	}

	public ComunidadException(Throwable cause) {
		super(cause);
	}
}
