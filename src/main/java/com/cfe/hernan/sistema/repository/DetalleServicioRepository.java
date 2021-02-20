package com.cfe.hernan.sistema.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cfe.hernan.sistema.model.DetalleServicio;

@Repository
public interface DetalleServicioRepository extends JpaRepository<DetalleServicio, Integer> {

	@Modifying
	@Transactional
	@Query(value = "Update detalle_servicio Set limite_pago=?, corte_luz=?,total_pago=?,"
			+ "id_cliente=?,id_comunidad=? where id_detalle_servicio=? ", nativeQuery = true)
	public void actualizarDetalleService(Date limite_pago, Date corte_luz, Double total_pago, Long id_cliente,
			int id_comunidad, int id_detalle_servicio);

	@Query(value = "SELECT * FROM detalle_servicio;", nativeQuery = true)
	public List<DetalleServicio> aListOfDetalleService();

	@Query(value = "select * from detalle_servicio where id_cliente=?", nativeQuery = true)
	public DetalleServicio obtengoDetallePorIdCliente(Long id_cliente);
}
