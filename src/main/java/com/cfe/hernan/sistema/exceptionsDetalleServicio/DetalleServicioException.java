package com.cfe.hernan.sistema.exceptionsDetalleServicio;

import org.apache.commons.lang.exception.NestableException;

public class DetalleServicioException extends NestableException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleServicioException() {
		super();
	}

	public DetalleServicioException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DetalleServicioException(String msg) {
		super(msg);
	}

	public DetalleServicioException(Throwable cause) {
		super(cause);
	}
}
