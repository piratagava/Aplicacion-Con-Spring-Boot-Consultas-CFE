package com.cfe.hernan.sistema.service;

import java.sql.Date;
import java.util.List;

import com.cfe.hernan.sistema.exceptionsDetalleServicio.DetalleServicioException;
import com.cfe.hernan.sistema.model.DetalleServicio;

public interface DetalleServicioService {

	public void guardarDetalleServicio(DetalleServicio detalleServicio) throws DetalleServicioException;

	public List<DetalleServicio> getDetalleServicio() throws DetalleServicioException;

	public void actualizarDetalleService(Date limite_pago, Date corte_luz, Double total_pago, Long id_cliente,
			int id_comunidad, int id_detalle_servicio) throws DetalleServicioException;

	public void eliminarDetalleService(DetalleServicio detalleServicio) throws DetalleServicioException;
	
	public List<DetalleServicio> aListOfDetalleServicio()  throws DetalleServicioException;

	public DetalleServicio obtengoDetallePorIdCliente(Long id_cliente) throws  DetalleServicioException;
}
