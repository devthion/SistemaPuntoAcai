package Propina;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class Propina {
	double monto;
	LocalDate fecha;
	int id;
		
	public Propina(double monto, LocalDate fecha) {
		this.monto = monto;
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public void modificarPropina(Propina propinaModificada) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.modificarPropina(propinaModificada);
	}
	
	public void eliminarPropina() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarPropina(this.getId());
	}
}
