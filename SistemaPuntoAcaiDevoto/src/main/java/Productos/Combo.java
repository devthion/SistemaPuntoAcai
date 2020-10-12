package Productos;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class Combo {

	String combo_nombre;
	double combo_precio;
	
	public Combo(String combo_nombre, double combo_precio) {
		this.combo_nombre = combo_nombre;
		this.combo_precio = combo_precio;
	}
	
	public String getCombo_nombre() {
		return combo_nombre;
	}
	public void setCombo_nombre(String combo_nombre) {
		this.combo_nombre = combo_nombre;
	}
	public double getCombo_precio() {
		return combo_precio;
	}
	public void setCombo_precio(double combo_precio) {
		this.combo_precio = combo_precio;
	}
	
	
	public void almacenar() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarCombo(this);
	}
	
	public void actualizarPrecio(double nuevoPrecio) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.actualizarPrecioCombo(nuevoPrecio, combo_nombre);
	}
	
	public void eliminar() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarCombo(this);
	}
	
	
}
