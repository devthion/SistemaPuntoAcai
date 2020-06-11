package Ventas;

import java.time.LocalDate;

public class Envio {
	String horario;
	LocalDate fecha;
	double precio;
	boolean entregado=false;
	
	public Envio(String horario, double precio, LocalDate fechaEntrega) {
		this.horario=horario;
		this.precio=precio;
		this.fecha=fechaEntrega;
	}
	
	public void setEstado(boolean unEstado) {
		this.entregado=unEstado;
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
