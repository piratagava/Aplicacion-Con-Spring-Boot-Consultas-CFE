package com.cfe.hernan.sistema.roleController;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cfe.hernan.sistema.exceptionsRole.RoleException;
import com.cfe.hernan.sistema.model.Role;
import com.cfe.hernan.sistema.service.RoleService;

@Controller
public class RoleController {

	private static final Logger log = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	private static final String ROLE_INSERT_DATA = "/insertarRole";
	private static final String LISTA_ALL_ROLE = "/listarRole";
	private static final String DELETE_ROLE = "/eliminarRole";
	private static final String UPDATE_ROLE = "/actualizarRole";
	private static final String CONSULTA_ID_ROLE = "/consultarIdRole";

	@GetMapping(value = CONSULTA_ID_ROLE)
	public @ResponseBody Integer consultaRole() throws RoleException {
		try {
			if (roleService.consultaId() == null || roleService.consultaId() == 0) {
				return 0;
			} else
				return roleService.consultaId();
		} catch (RoleException e) {
			return 0;
		}
	}

	@PostMapping(value = ROLE_INSERT_DATA)
	public @ResponseBody Boolean insertarRoles(@RequestBody Role role) {
		try {
			roleService.guardarRole(role);
			return true;
		} catch (RoleException e) {
			log.warn("ERROR AL GUARDAR ENTRO EN EL CONTROLLER");
			System.out.print(e.getMessage());
			return false;
		}
	}

	// @ResponseBody es importante ya que si no pues no regresa nada jajaja
	@GetMapping(value = LISTA_ALL_ROLE)
	public @ResponseBody List<Role> listarRoles() throws RoleException {
		try {
			List<Role> listarRoles = roleService.listarRoles();
			return listarRoles;
			// return (ResponseEntity<List<Role>>) listarRoles;
		} catch (RoleException e) {
			System.out.print("ERROR EN LISTAR ROLES POR " + e.toString());
			System.out.print("MENSAJE DE ERROR INTERNO NO SE PUDO LISTAR LOS ROLES " + e.getMessage());
			return null;
		}

	}

	@DeleteMapping(value = DELETE_ROLE)
	public @ResponseBody Boolean deleteRole(@RequestBody Role role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			roleService.deleteRole(role.getId_role());
			return true;
		} catch (RoleException ex) {
			mensaje.append("NO SE PUDO ELIMINAR ROLE ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE BORRAR CLIENTE CONTROLLER");
			throw new RoleException(ex.getMessage());
		}
	}

	@PutMapping(value = UPDATE_ROLE)
	public @ResponseBody Boolean updateRole(@RequestBody Role role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			roleService.actualizarRole(role.getName(), role.getId_role());
			return true;
		} catch (RoleException ex) {
			mensaje.append("NO SE PUDO ACTUALIZAR CLIENTE ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR CLIENTE CONTROLLER");
			throw new RoleException(ex.getMessage());
		}
	}
}
