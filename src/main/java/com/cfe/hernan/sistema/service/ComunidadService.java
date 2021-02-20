package com.cfe.hernan.sistema.service;

import java.util.List;
import com.cfe.hernan.sistema.exceptionsComunidad.ComunidadException;
import com.cfe.hernan.sistema.model.Comunidad;

public interface ComunidadService {
	public void guardarComunidad(Comunidad comunidad) throws ComunidadException;

	public List<Comunidad> listarComunidades() throws ComunidadException;

	public void deleteComunidad(Comunidad comunidad) throws ComunidadException;
}
