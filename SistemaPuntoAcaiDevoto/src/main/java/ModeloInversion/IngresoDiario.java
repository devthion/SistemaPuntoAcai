package ModeloInversion;

import java.sql.SQLException;
import java.time.LocalDate;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class IngresoDiario {
	private LocalDate fecha;
	private String detalle;
	private double monto;
	private int id;
	
	
	public IngresoDiario(String detalle, double monto) {
		this.fecha=LocalDate.now();
		this.detalle=detalle;
		this.monto=monto;
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

	public String getDetalle() {
		return detalle;
	}

	public double getMonto() {
		return monto;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha=fecha;
	}

	public void almacenarIngreso() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarIngresoDiario(this);
	}
	
	public void modificarIngreso(IngresoDiario ingresoModificado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarIngresoDiario(this.getId(),ingresoModificado);
	}
	
	public void eliminarIngreso() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarIngresoDiario(this.getId());
	}
}
