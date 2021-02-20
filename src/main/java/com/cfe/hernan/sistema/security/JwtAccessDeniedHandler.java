package com.cfe.hernan.sistema.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// Esto se invoca cuando el usuario intenta acceder a un recurso REST protegido sin la autorización necesaria
		// Deberíamos enviar una respuesta 403 Prohibida porque no hay una página de
		//'error' a la que redirigir
		// Aquí puedes colocar cualquier mensaje que quieras
		response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
	}

}
