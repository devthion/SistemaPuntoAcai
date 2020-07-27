package Propina;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import ConexionBD.InsertarDatos;

public class Propina {
	double monto;
	LocalDate fecha;
		
	public Propina(double monto, LocalDate fecha) {
		this.monto = monto;
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public void guardarPropina() {
		try {
			InsertarDatos insertarDatos = new InsertarDatos();
			insertarDatos.insertarPropina(this);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}