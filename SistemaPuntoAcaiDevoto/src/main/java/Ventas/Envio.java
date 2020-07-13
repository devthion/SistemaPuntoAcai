package Ventas;

import java.time.LocalDate;

public class Envio {
	String horario;
	LocalDate fecha ;
	double precio;
	boolean entregado=false;
	String observacion;
	
	public Envio(String horario, double precio, LocalDate fechaEntrega, String observacion) {
		this.horario=horario;
		this.precio=precio;
		this.fecha=fechaEntrega;
		this.observacion = observacion;
	}
	
	public void setEstado(boolean unEstado) {
		this.entregado=unEstado;
	}
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void concretarEnvio() {
		this.entregado=true;
	}
	
	public boolean getEstado() {
		return entregado;
	}

	public double getPrecio() {
		return precio;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public LocalDate getFechaEntrega() {
		return fecha;
	}
	
}
