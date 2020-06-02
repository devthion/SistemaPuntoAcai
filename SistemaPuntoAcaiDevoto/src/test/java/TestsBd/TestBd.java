package TestsBd;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ConexionBD.ConexionBd;
import ConexionBD.InsertarDatos;
import ConexionBD.ObtenerDatos;
import ConexionBD.PruebaInsertarMostrarHibernate;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import Ventas.Venta;

public class TestBd {
	ObtenerDatos obtenerDatosImpostor = Mockito.mock(ObtenerDatos.class);
	InsertarDatos insertarDatosImpostor = Mockito.mock(InsertarDatos.class);
	ConexionBd conexionBdImpostor = Mockito.mock(ConexionBd.class);
	Venta venta;
	//PruebaInsertarMostrarHibernate hibernate;

	 /* @InjectMocks private ConexionBd conexionBd;
	  @Mock private Connection mockConnection;
	  @Mock private Statement mockStatement;
	 
	  @Before
	  public void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	 
	  @Test
	  public void testMockDBConnection() throws Exception {
	    Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
	    Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
	    Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
	  }
	*/
	
	@Test
	public void ejecutarQueryEjecutaLaQuery() throws SQLException {
		obtenerDatosImpostor.ejecutarQuery(Mockito.any(), Mockito.any());
		
		Mockito.verify(obtenerDatosImpostor, Mockito.only()).ejecutarQuery(Mockito.any(), Mockito.any());
		
	}
	
	@Test
	public void insertarClienteEjecutaUpdateQuery() throws SQLException {
		insertarDatosImpostor.insertarCliente(Mockito.any());
		
		Mockito.verify(conexionBdImpostor, Mockito.only()).ejecutarUpdate(Mockito.any(), Mockito.any());
	
	}

	
	/*@Test
	public void obtengoClienteHibernate() throws SQLException {
		hibernate = new PruebaInsertarMostrarHibernate();
		assert(hibernate.);
	}*/
	
}
