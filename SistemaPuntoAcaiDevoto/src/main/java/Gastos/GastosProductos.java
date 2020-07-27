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
		insertarDatos.insertarGasto(this, "GASTO_PRODUCTO");
	}
	
	public void editarGasto(GastosProductos gastoModificado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarGasto(this.getId(), gastoModificado, "GASTO_PRODUCTO");
	}
	
	public void eliminarGasto() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarGasto(this.getId(),"GASTO_PRODUCTO");
	}
	
}
