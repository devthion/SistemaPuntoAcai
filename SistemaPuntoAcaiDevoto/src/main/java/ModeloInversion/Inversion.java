package ModeloInversion;

import java.sql.SQLException;
import java.time.LocalDate;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class Inversion {
	
	private LocalDate fecha;
	private String detalle;
	private double monto;
	private int id;
	
	
	public Inversion(String detalle, double monto) {
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

	public void almacenarInversion() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarInversion(this);
	}
	
	public void modificarInversion(Inversion inversionModificada) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarInversion(this.getId(),inversionModificada);
	}
	
	public void eliminate() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarInversion(this.getId());
	}
	
}
