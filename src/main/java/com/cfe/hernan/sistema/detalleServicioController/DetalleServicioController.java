package com.cfe.hernan.sistema.detalleServicioController;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cfe.hernan.sistema.exceptionsComunidad.ComunidadException;
import com.cfe.hernan.sistema.exceptionsDetalleServicio.DetalleServicioException;
import com.cfe.hernan.sistema.model.Comunidad;
import com.cfe.hernan.sistema.model.DetalleServicio;
import com.cfe.hernan.sistema.service.DetalleServicioService;

@Controller
public class DetalleServicioController {
	private static final Logger log = Logger.getLogger(DetalleServicioController.class);

	@Autowired
	private DetalleServicioService detalleService;

	private static final String DETALLE_SERVICIO_INSERT_DATA = "/insertarDetalleService";
	private static final String LISTA_ALL_DETALLESERVICIO = "/listaDetalleServicio";
	private static final String UPDATE_SERVICIO_INSERT_DATA = "/actualizarDetalleService";
	private static final String DELETE_SERVICIO_INSERT_DATA = "/deleteDetalleService";
	private static final String CONSULTA_DETALLE_SERVICIO_CLIENTE = "/api/consultaDetalleServicioCliente";

	@PostMapping(value = DETALLE_SERVICIO_INSERT_DATA)
	public @ResponseBody Boolean insertarDetalleService(@RequestBody DetalleServicio detalleServicio) {
		try {
			detalleService.guardarDetalleServicio(detalleServicio);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@PostMapping(value = CONSULTA_DETALLE_SERVICIO_CLIENTE)
	public @ResponseBody DetalleServicio obtengoDetalleService(@RequestBody DetalleServicio dService)
			throws DetalleServicioException {
		System.out.println("mijooo soy la " + dService);
		System.out.println("mijooo soy la consulta haber que pasa aqui"
				+ detalleService.obtengoDetallePorIdCliente(dService.getCliente().getId_cliente()));
		DetalleServicio obtengoConsulta = detalleService
				.obtengoDetallePorIdCliente(dService.getCliente().getId_cliente());
		if (obtengoConsulta == null) {
			return obtengoConsulta=null;			
		} else {
			return obtengoConsulta;
		}
	}

	@PutMapping(value = UPDATE_SERVICIO_INSERT_DATA)
	public @ResponseBody Boolean actualizarDetalleService(@RequestBody DetalleServicio detalleServicio) {
		try {
			System.out.print(detalleServicio);
			detalleService.actualizarDetalleService(detalleServicio.getLimite_pago(), detalleServicio.getCorte_luz(),
					detalleServicio.getTotal_pago(), detalleServicio.getCliente().getId_cliente(),
					detalleServicio.getComunidad().getId_comunidad(), detalleServicio.getId_detalle_servicio());
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	@DeleteMapping(value = DELETE_SERVICIO_INSERT_DATA)
	public @ResponseBody Boolean deleteDetalleService(@RequestBody DetalleServicio detalleServicio)
			throws DetalleServicioException {
		StringBuilder mensaje = new StringBuilder();
		try {
			detalleService.eliminarDetalleService(detalleServicio);
			return true;
		} catch (DetalleServicioException ex) {
			mensaje.append("NO SE PUDO ELIMINAR COMUNIDAD ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE BORRAR COMUNIDAD CONTROLLER");
			throw new DetalleServicioException(ex.getMessage());
		}
	}

	@GetMapping(value = LISTA_ALL_DETALLESERVICIO)
	public @ResponseBody List<DetalleServicio> listarDetalleService() throws DetalleServicioException {
		try {
			List<DetalleServicio> listar = detalleService.aListOfDetalleServicio();
			System.out.println("SOY LISTA ESTO  LLEVO" + listar);
			return listar;
		} catch (DetalleServicioException e) {
			System.out.print("ERROR EN LISTAR POR " + e.toString());
			System.out.print("MENSAJE DE ERROR INTERNO NO SE PUDO LISTAR LOS DETALLES SERVICE " + e.getMessage());
			return null;
		}
	}
}
