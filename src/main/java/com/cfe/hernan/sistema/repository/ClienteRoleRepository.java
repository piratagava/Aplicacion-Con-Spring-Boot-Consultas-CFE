package com.cfe.hernan.sistema.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cfe.hernan.sistema.model.ClienteRole;
import com.cfe.hernan.sistema.model.ClienteRolePK;

@Repository
public interface ClienteRoleRepository extends JpaRepository<ClienteRole, ClienteRolePK> {

	@Query(value = " SELECT id_cliente, username,apellido_paterno,apellido_materno,name FROM user_authority INNER JOIN  cliente using(id_cliente);", nativeQuery = true)
	public List<Object> aListOfClienteRole();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM user_authority WHERE id_cliente=?;", nativeQuery = true)
	public void eliminarPorIdCliente(int id_cliente);

	@Modifying
	@Transactional
	@Query(value = "Update user_authority Set name=? where id_cliente=? ", nativeQuery = true)
	public void actualizarClienteRole(String role, int id_cliente);

}
