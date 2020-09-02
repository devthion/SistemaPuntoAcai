package Egresos;

import java.sql.SQLException;
import java.time.LocalDate;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class Egreso {
	private LocalDate fecha;
	private String detalle;
	private double monto;
	private int id;
	
	public Egreso(String detalle, double monto) {
		super();
		this.fecha = LocalDate.now();
		this.detalle = detalle;
		this.monto = monto;
	}
	
	public void almacenarEgreso() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarEgreso(this);
	}
	
	public void editarEgreso(Egreso egresoModificado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarEgreso(this.getId(), egresoModificado);
	}
	
	public void eliminarEgreso() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarEgreso(this.getId());
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
