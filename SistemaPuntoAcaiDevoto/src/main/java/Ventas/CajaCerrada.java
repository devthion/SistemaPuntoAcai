package Ventas;

import java.sql.SQLException;
import java.time.LocalDate;

import ConexionBD.InsertarDatos;

public class CajaCerrada {
	
	LocalDate fecha;
	double monto_real;
	double monto_ideal;
	InsertarDatos insertarDatos;
	
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
	
	public void almacenarCajaCerrada() throws SQLException {
		insertarDatos = new InsertarDatos();
		insertarDatos.insertarCajaCerrada(this);
	}
	
	

}
