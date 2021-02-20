package com.cfe.hernan.sistema.service.implement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfe.hernan.sistema.exceptionsCliente.ClienteException;
import com.cfe.hernan.sistema.exceptionsRole.RoleException;
import com.cfe.hernan.sistema.model.Role;
import com.cfe.hernan.sistema.repository.RoleRepository;
import com.cfe.hernan.sistema.service.RoleService;

@Service
public class RoleServiceImplement implements RoleService {

	private static final Logger log = Logger.getLogger(RoleService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void guardarRole(Role role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (role.getName().equals(null) || role.getName() == "" || role.getName().length() > 40) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE VACIO O SE PASO DE LIMITE DE CARACTERES");
				throw new RoleException("EL DATO VACIO ES :" + role.getName() + " O ES MUCHOS CARACTERES");
			} else
				roleRepository.save(role);
		} catch (RoleException e) {
			mensaje.append("NO SE PUDO GUARDAR EL ROLE ERROR INTERNO").append(e.getMessage());
			log.error("ERROR INTERNO ENTRO EN EL CATCH DE GUARDAR ROLE");
			throw new RoleException(e.getMessage());
		}
	}

	@Override
	public List<Role> listarRoles() throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		List<Role> guardarLista = roleRepository.findAll();
		if (guardarLista.equals(null)) {
			log.warn("NO EXISTEN DATOS EN BD PARA LISTAR ROLES");
			mensaje.append("LA LISTA DEL ROLE SE ENCUENTRA VACIA");
		}
		return guardarLista;
	}

	@Override
	public Integer consultaId() throws RoleException {
		return roleRepository.consultaIdRoleTotal();

	}

	@Override
	public void deleteRole(int role) throws RoleException {
		roleRepository.eliminarPorIdRole(role);
	}

	@Override
	public void actualizarRole(String role,int id_role) throws RoleException {
		roleRepository.actualizarPorNombreRole(role, id_role);
	}

}
