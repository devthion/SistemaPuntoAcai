package ModeloInversion;

import java.sql.SQLException;
import java.time.LocalDate;

import ConexionBD.InsertarDatos;

public class Inversion {
	
	private LocalDate fecha;
	private String detalle;
	private double monto;
	
	
	public Inversion(String detalle, double monto) {
		this.fecha=LocalDate.now();
		this.detalle=detalle;
		this.monto=monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public double getMonto() {
		return monto;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha=fecha;
	}

	public void almacenarInversion() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarInversion(this);
	}
	
}
