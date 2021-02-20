package com.cfe.hernan.sistema.homeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePage {

	private static final String Home_page = "/index";
	private static final String Admin_page = "/es-login-page-Ing_Hernan";
	private static final String Alta_Cliente = "/AltaCliente";
	private static final String Alta_Role = "/AltaRole";
	private static final String Alta_Comunidad = "/AltaComunidad";
	private static final String Alta_Cliente_Role = "/ClienteRole";
	private static final String Alta_Detalle_Servicio = "/DetalleServicio";

	@GetMapping(value = Home_page)
	public ModelAndView Default() {
		ModelAndView modelAndView = new ModelAndView("index.jsp");
		return modelAndView;
	}

	@GetMapping(value = Admin_page)
	public String Admin() {
		return "Administrador-Page/Administrador";
	}
	@GetMapping(value = Alta_Cliente)
	public String AltaCliente() {
		return "Administrador-Page/AltaCliente";
	}

	@GetMapping(value = Alta_Role)
	public String AltaRole() {
		return "Administrador-Page/AltaRoles";
	}

	@GetMapping(value = Alta_Cliente_Role)
	public String AltaClienteRole() {
		return "Administrador-Page/ClienteRole";
	}

	@GetMapping(value = Alta_Comunidad)
	public String AltaComunidad() {
		return "Administrador-Page/AltaComunidad";
	}

	@GetMapping(value = Alta_Detalle_Servicio)
	public String AltaDetalleServicio() {
		return "Administrador-Page/DetalleServicio";
	}

}
