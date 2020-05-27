package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import ModelosGraficos.ClientesPorBarrio;
import Productos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObtenerDatos extends ConexionBd{
	ResultSet rs;
	String sql;

	public ObtenerDatos() throws SQLException {
		super();
	}

	public ObservableList<Cliente> obtenerClientes() throws SQLException {
		ObservableList<Cliente> clientes = FXCollections.observableArrayList();
		
	
		sql = "select * from CLIENTE";
		rs = ejecutarQuery(sql);
		while(rs.next()) {
			Direccion unaDireccion= new Direccion(rs.getString(10),rs.getInt(9),rs.getString(8),rs.getInt(7));
			Cliente unCliente = new Cliente(rs.getInt(4),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(6),unaDireccion,rs.getString(1),rs.getString(11),ingresosGeneradosPor(rs.getInt(4)));
			clientes.add(unCliente);
		}
		
		return clientes;
	}
	
	
	public double ingresosGeneradosPor(int clie_dni) throws SQLException {
		
		/*sql = "SELECT isnull(SUM(venta_precioTotal),0) FROM VENTA "
				+ "WHERE venta_cliente = '"+clie_dni+"'";
		rs=ejecutarQuery(sql);*/
		
		return 1;
	}
	
	public ObservableList<Producto> obtenerProductos() throws SQLException{
		ObservableList<Producto> productos = FXCollections.observableArrayList();
		
		sql="SELECT * FROM PRODUCTO";
		rs=ejecutarQuery(sql);
		while(rs.next()) {
			Producto unProducto = new Producto(rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7), rs.getInt(8));
			unProducto.setProd_id(rs.getInt(1));
			productos.add(unProducto);
		}
		return productos;
		
	}
	
	
	public List<ClientesPorBarrio> obtenerClientesPorBarrio() throws SQLException{
		List<ClientesPorBarrio> clientesPorBarrioList = new ArrayList<ClientesPorBarrio>();
		sql="SELECT dire_barrio,COUNT(dire_barrio) FROM CLIENTE "
				+ "GROUP BY dire_barrio";
		rs=ejecutarQuery(sql);
		while(rs.next()) {
			ClientesPorBarrio clientesPorBarrio = new ClientesPorBarrio(rs.getString(1), rs.getInt(2));
			clientesPorBarrioList.add(clientesPorBarrio);
		}
	
		return clientesPorBarrioList;
	}
	
	public int obtenerIdUltimaVentaIngresada() throws SQLException {
		sql="SELECT top 1 venta_id FROM VENTA ORDER BY venta_id desc";
		rs=ejecutarQuery(sql);
		return rs.getFetchSize();
		//return rs.getInt(1);
	}
	
	
}
