package Ventas;

import java.time.LocalDate;

public class CajaCerrada {
	
	LocalDate fecha;
	double monto_real;
	double monto_ideal;
	
	public CajaCerrada(LocalDate fecha,double monto_real,double monto_ideal) {
		this.fecha=fecha;
		this.monto_ideal=monto_ideal;
		this.monto_real=monto_real;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public double getMonto_real() {
		return monto_real;
	}
	public double getMonto_ideal() {
		return monto_ideal;
	}
	
	

}
