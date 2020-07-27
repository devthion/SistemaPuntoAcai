package Gastos;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;

public class GastosDiarios extends Gasto {
	
	public GastosDiarios(String detalle, double monto) {
		super(detalle, monto);
	}
	
	public void almacenarGasto() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarGasto(this, "GASTO_DIARIO");
	}
	
	public void editarGasto(Gasto gastoModificado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarGasto(this.getId(), gastoModificado, "GASTO_DIARIO");
	}
	
	public void eliminarGasto() throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.eliminarGasto(this.getId(),"GASTO_DIARIO");;
	}
}
