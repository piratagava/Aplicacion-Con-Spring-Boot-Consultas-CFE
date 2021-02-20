package com.cfe.hernan.sistema.model;

import java.io.Serializable;
import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_servicio")
public class DetalleServicio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO ) 
	private int id_detalle_servicio;

	@Column(name = "limite_pago")
	@NotNull(message = "Limite de pago no debe ir vacio")
	private Date limite_pago;

	@Column(name = "corte_luz")
	@NotNull(message = "corte_luz no debe ir vacio")
	private Date corte_luz;

	@Column(name = "total_pago")
	@NotNull(message = "total_pago no debe ir vacio")
	private double total_pago;

	// Relacion Para detalle Servicio es mejor hacerlo rapudo con Eager
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_comunidad", nullable = false)
	private Comunidad comunidad;

	// Relacion Para detalle Servicio es mejor hacerlo rapudo con Eager
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	public DetalleServicio() {
		DetalleServicio detalleServicio;
	}

	public DetalleServicio(int id_detalle_servicio, Date limite_pago, Date corte_luz, double total_pago,
			Comunidad comunidad, Cliente cliente) {
		super();
		this.id_detalle_servicio = id_detalle_servicio;
		this.limite_pago = limite_pago;
		this.corte_luz = corte_luz;
		this.total_pago = total_pago;
		this.comunidad = comunidad;
		this.cliente = cliente;
	}

	public int getId_detalle_servicio() {
		return id_detalle_servicio;
	}

	public void setId_detalle_servicio(int id_detalle_servicio) {
		this.id_detalle_servicio = id_detalle_servicio;
	}

	public Date getLimite_pago() {
		return limite_pago;
	}

	public void setLimite_pago(Date limite_pago) {
		this.limite_pago = limite_pago;
	}

	public Date getCorte_luz() {
		return corte_luz;
	}

	public void setCorte_luz(Date corte_luz) {
		this.corte_luz = corte_luz;
	}

	public double getTotal_pago() {
		return total_pago;
	}

	public void setTotal_pago(double total_pago) {
		this.total_pago = total_pago;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "DetalleServicio [id_detalle_servicio=" + id_detalle_servicio + ", limite_pago=" + limite_pago
				+ ", corte_luz=" + corte_luz + ", total_pago=" + total_pago + ", comunidad=" + comunidad + ", cliente="
				+ cliente + "]";
	}

}
