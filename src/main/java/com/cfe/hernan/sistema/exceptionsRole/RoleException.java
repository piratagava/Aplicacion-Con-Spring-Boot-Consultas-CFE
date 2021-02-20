package com.cfe.hernan.sistema.exceptionsRole;

import org.apache.commons.lang.exception.NestableException;

public class RoleException extends NestableException{
	private static final long serialVersionUID = 1L;

	public RoleException() {
        super();
    }

	public RoleException(String msg, Throwable cause) {
        super(msg, cause);
    }

	public RoleException(String msg) {
        super(msg);
    }

	public RoleException(Throwable cause) {
        super(cause);
    }
}
