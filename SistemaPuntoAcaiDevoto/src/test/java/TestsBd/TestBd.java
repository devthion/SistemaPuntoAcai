package TestsBd;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import ConexionBD.ObtenerDatos;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import Ventas.Venta;

public class TestBd {
	ObtenerDatos obtenerDatos;
	Venta venta;
	
	@Test
	public void obtengoVentas() throws SQLException {
		obtenerDatos = new ObtenerDatos();
		assert(obtenerDatos.obtenerVentas().size()!=0);
	}
	
}
