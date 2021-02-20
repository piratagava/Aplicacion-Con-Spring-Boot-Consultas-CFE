package com.cfe.hernan.sistema.comunidadController;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cfe.hernan.sistema.exceptionsComunidad.ComunidadException;
import com.cfe.hernan.sistema.model.Comunidad;
import com.cfe.hernan.sistema.service.ComunidadService;

@Controller
public class ComunidadController {
	private static final Logger log = Logger.getLogger(ComunidadController.class);

	@Autowired
	private ComunidadService comunidadService;

	private static final String COMUNIDAD_INSERT_DATA = "/insertarComunidad";
	private static final String LISTA_ALL_COMUNIDAD = "/listaComunidad";
	private static final String UPDATE_COMUNIDAD = "/actualizarComunidad";
	private static final String DELETE_COMUNIDAD = "/eliminarComunidad";

	@PostMapping(value = COMUNIDAD_INSERT_DATA)
	public @ResponseBody Boolean insertarComunidad(@RequestBody Comunidad comunidad) {
		try {
			comunidadService.guardarComunidad(comunidad);
			return true;
		} catch (ComunidadException e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@GetMapping(value = LISTA_ALL_COMUNIDAD)
	public @ResponseBody List<Comunidad> listarComunidad() throws ComunidadException {
		try {
			List<Comunidad> listar = comunidadService.listarComunidades();
			return listar;
		} catch (ComunidadException e) {
			System.out.print("ERROR EN LISTAR POR " + e.toString());
			System.out.print("MENSAJE DE ERROR INTERNO NO SE PUDO LISTAR LAS COMUNIDADES " + e.getMessage());
			return null;
		}

	}

	@DeleteMapping(value = DELETE_COMUNIDAD)
	public @ResponseBody Boolean deleteComunidad(@RequestBody Comunidad comunidad) throws ComunidadException {
		StringBuilder mensaje = new StringBuilder();
		try {
			comunidadService.deleteComunidad(comunidad);
			return true;
		} catch (ComunidadException ex) {
			mensaje.append("NO SE PUDO ELIMINAR COMUNIDAD ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE BORRAR COMUNIDAD CONTROLLER");
			throw new ComunidadException(ex.getMessage());
		}
	}

	@PutMapping(value = UPDATE_COMUNIDAD)
	public @ResponseBody Boolean updateComunidad(@RequestBody Comunidad comunidad) throws ComunidadException {
		StringBuilder mensaje = new StringBuilder();
		try {
			comunidad.setId_comunidad(comunidad.getId_comunidad());
			comunidad.setDireccion(comunidad.getDireccion());
			comunidadService.guardarComunidad(comunidad);
			return true;
		} catch (ComunidadException e) {
			mensaje.append("NO SE PUDO ACTUALIZAR COMUNIDAD ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR COMUNIDAD CONTROLLER");
			throw new ComunidadException(e.getMessage());
		}

	}

}
