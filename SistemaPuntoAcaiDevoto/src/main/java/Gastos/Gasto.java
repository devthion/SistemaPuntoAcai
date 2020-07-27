package Gastos;

import java.time.LocalDate;

public abstract class Gasto {
	private LocalDate fecha;
	private String detalle;
	private double monto;
	private int id;
	
	public Gasto(String detalle, double monto) {
		super();
		this.fecha = LocalDate.now();
		this.detalle = detalle;
		this.monto = monto;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
}
