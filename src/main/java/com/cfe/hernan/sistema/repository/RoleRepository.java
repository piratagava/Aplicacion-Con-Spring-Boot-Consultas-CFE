package com.cfe.hernan.sistema.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cfe.hernan.sistema.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	@Query(value="SELECT MAX(id_role) AS id FROM authority;",nativeQuery=true)     
	public int consultaIdRoleTotal();
		
	@Modifying
    @Transactional
	@Query(value="DELETE FROM authority WHERE id_role=?;",nativeQuery=true)     
	public void eliminarPorIdRole(int id_role);
	
	@Modifying
	@Transactional
	@Query(value="Update authority Set name=? where id_role=? ",nativeQuery=true)
	public void actualizarPorNombreRole(String role, int id_role);
}
