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
			Direccion unaDireccion= new Direccion(rs.getString(11),rs.getInt(10),rs.getString(9),rs.getInt(8));
			Cliente unCliente = new Cliente(rs.getInt(5),rs.getString(3),rs.getString(4),rs.getInt(6),rs.getString(7),unaDireccion,rs.getString(2),rs.getString(12),ingresosGeneradosPor(rs.getInt(1)));
			clientes.add(unCliente);
		}
		
		return clientes;
	}
	
	
	public double ingresosGeneradosPor(int unId) {
		return 1;
	}
	
	public ObservableList<Producto> obtenerProductos() throws SQLException{
		ObservableList<Producto> productos = FXCollections.observableArrayList();
		
		sql="SELECT * FROM PRODUCTO";
		rs=ejecutarQuery(sql);
		while(rs.next()) {
			Producto unProducto = new Producto(rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7), rs.getInt(8));
			productos.add(unProducto);
		}
		return productos;
		
	}
}
