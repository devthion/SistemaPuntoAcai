package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ModelosClientes.Cliente;
import Productos.Producto;

public class InsertarDatos extends ConexionBd{

	public InsertarDatos() throws SQLException {
		super();
	}
	
	
	public void insertarCliente(Cliente unCliente) {
		String sql = "insert into CLIENTE"
				+ "(clie_tipo,"
				+ "clie_nombre, "
				+ "clie_apellido, "
				+ "clie_dni, "
				+ "clie_telefono, "
				+ "clie_email, "
				+ "dire_codPostal, "
				+ "dire_barrio,"
				+ "dire_numero,"
				+ "dire_calle,"
				+ "clie_como_llego) "
				+ "values('"+unCliente.getTipo()+"','"+unCliente.getNombre()+"','"+unCliente.getApellido()+"','"+unCliente.getDni()+"','"+unCliente.getTelefono()+"','"+unCliente.getEmail()+"','"+unCliente.getDireccion().getCodPostal()+"','"+unCliente.getDireccion().getBarrio()+"','"+unCliente.getDireccion().getNumero()+"','"+unCliente.getDireccion().getCalle()+"','"+unCliente.getComoLlego()+"')"; 
		ejecutarUpdate(sql, "Cliente ingresado");	
	}
	
	

	public void insertarProducto(Producto unProducto) {
		String sql = "insert into PRODUCTO(prod_nombre, prod_kilos, prod_stock, prod_precio, prod_precio_mayor, prod_costo) "
				+ "values('"+unProducto.getNombre()+"','"+unProducto.getKilos()+"','"+unProducto.getStock()+"','"+unProducto.getPrecioUnitario()+"','"+unProducto.getPrecioMayor()+"','"+unProducto.getCosto()+"')"; 
		
		ejecutarUpdate(sql, "Producto ingresado");
		
	}
	
	/*
	public void insertarVenta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			//String sql = "insert into CLIENTE(nombre, apellido) values('Diego', 'vivona')"; 
			stmt.executeUpdate(sqlQuery);
			System.out.println("Datos ingresados");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void insertarItemVenta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			//String sql = "insert into CLIENTE(nombre, apellido) values('Diego', 'vivona')"; 
			stmt.executeUpdate(sqlQuery);
			System.out.println("Datos ingresados");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}*/
}
