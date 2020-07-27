package Gastos;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class GastosGenerales extends Gasto {
	
	public GastosGenerales(String detalle, double monto) {
		super(detalle, monto);
	}
	
	public void almacenarGasto() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarGasto(this, "GASTO_GENERAL");
	}
	
	public void editarGasto(Gasto gastoModificado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarGasto(this.getId(), gastoModificado, "GASTO_GENERAL");
	}
	
	public void eliminarGasto() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarGasto(this.getId(),"GASTO_GENERAL");
	}
}
