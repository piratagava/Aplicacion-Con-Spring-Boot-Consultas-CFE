package com.cfe.hernan.sistema.repository;

import com.cfe.hernan.sistema.model.Cliente;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "SELECT MAX(id_cliente) AS id FROM cliente;", nativeQuery = true)
	public int consultaIdCliente();

	@Modifying
	@Transactional // sirve para no iniciarlizar con begin y cerrar con commit
	@Query(value = "Update cliente Set username=?, apellido_paterno=?,apellido_materno=?, activo=?, password=? where id_cliente=?", nativeQuery = true)
	public void actualizarCliente(String nombre, String apellidoP, String apellidoM, Boolean activo, String password,
			Long id_cliente);

	 @EntityGraph(attributePaths = "authorities")
	   Optional<Cliente> findOneWithAuthoritiesByUsername(String username);
	// consulta se ejecutará como un join. Es mejorar el rendimiento en tiempo de
	// ejecución al cargar las asociaciones relacionadas y los campos básicos de la entidad.

	 @Query(value = "SELECT * FROM cliente t1 WHERE NOT EXISTS (SELECT NULL FROM detalle_servicio t2 WHERE t2.id_cliente = t1.id_cliente)", nativeQuery = true)
	 public List<Cliente> consultaClienteNoAsociadosDetalleService();
}
