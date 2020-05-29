package TestsBd;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import ConexionBD.ObtenerDatos;
import ConexionBD.PruebaInsertarMostrarHibernate;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import Ventas.Venta;

public class TestBd {
	ObtenerDatos obtenerDatosImpostor = Mockito.mock(ObtenerDatos.class);
	Venta venta;
	//PruebaInsertarMostrarHibernate hibernate;

	
	
	
	@Test
	public void obtengoVentas() throws SQLException {
		obtenerDatosImpostor.ejecutarQuery(Mockito.any(), Mockito.any());
		
		Mockito.verify(obtenerDatosImpostor, Mockito.only()).ejecutarQuery(Mockito.any(), Mockito.any());
		//assert(obtenerDatos.obtenerVentas().size()!=0);
	}
	
	/*@Test
	public void obtengoClienteHibernate() throws SQLException {
		hibernate = new PruebaInsertarMostrarHibernate();
		assert(hibernate.);
	}*/
	
}
