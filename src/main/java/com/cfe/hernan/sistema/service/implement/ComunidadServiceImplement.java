package com.cfe.hernan.sistema.service.implement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfe.hernan.sistema.exceptionsComunidad.ComunidadException;
import com.cfe.hernan.sistema.exceptionsRole.RoleException;
import com.cfe.hernan.sistema.model.Comunidad;
import com.cfe.hernan.sistema.repository.ComunidadRepository;
import com.cfe.hernan.sistema.service.ComunidadService;

@Service
public class ComunidadServiceImplement implements ComunidadService {

	private static final Logger log = Logger.getLogger(ComunidadServiceImplement.class);

	@Autowired
	private ComunidadRepository comunidadRepository;

	@Override
	public void guardarComunidad(Comunidad comunidad) throws ComunidadException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (comunidad.getDireccion().equals(null) || comunidad.getDireccion() == "") {
				log.warn("UNO DE LOS ATRIBUTOS VIENE VACIO O SE PASO DE LIMITE DE CARACTERES");
				throw new RoleException("EL DATO VACIO ES :" + comunidad.getDireccion() + "O ES MUCHOS CARACTERES");
			} else
				comunidadRepository.save(comunidad);
		} catch (RoleException e) {
			mensaje.append("NO SE PUDO GUARDAR LA COMUNIDAD ERROR INTERNO").append(e.getMessage());
			log.error("ERROR INTERNO ENTRO EN EL CATCH DE GUARDAR COMUNIDAD");
			throw new ComunidadException(e.getMessage());
		}
	}

	@Override
	public List<Comunidad> listarComunidades() throws ComunidadException {
		StringBuilder mensaje = new StringBuilder();
		List<Comunidad> guardarLista = comunidadRepository.findAll();
		if (guardarLista.equals(null)) {
			log.warn("NO EXISTEN DATOS EN BD PARA LISTAR ROLES");
			mensaje.append("LA LISTA DEL ROLE SE ENCUENTRA VACIA");
		}
		return guardarLista;
	}

	@Override
	public void deleteComunidad(Comunidad comunidad) throws ComunidadException {
		comunidadRepository.delete(comunidad);

	}

}
