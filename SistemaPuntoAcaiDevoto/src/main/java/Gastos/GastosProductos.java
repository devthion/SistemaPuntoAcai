package Gastos;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class GastosProductos extends Gasto {

	public GastosProductos(String detalle, double monto) {
		super(detalle, monto);
	}
	
	public void almacenarGasto() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarGastoProducto(this);
	}
	
	public void editarGasto(GastosProductos gastoModificado) throws SQLException {
		new ModificarDatos().editarGastoProducto(this, gastoModificado);
	}
	
	public void eliminarGasto() throws SQLException {
		new ModificarDatos().eliminarGastoProducto(this);
	}
	
}
