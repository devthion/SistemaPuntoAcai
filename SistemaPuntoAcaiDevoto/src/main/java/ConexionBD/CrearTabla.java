package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearTabla {

	public static void main(String[]args) {
		crearTablaProductos();
		crearTablaClientes();
		crearTablaVenta();
	}
	
	public static void crearTablaVenta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CLIENTE" 
				+"(venta_id INTEGER auto_increment,"
				+"venta_cliente INTEGER(10),"
				+"venta_fecha DATE,"
				+"venta_precioTotal DOUBLE(10),"
				+"venta_ganancia DOUBLE(10),"
				+"PRIMARY KEY (clie_dni),"
				+"FOREIGN KEY (venta_cliente) REFERENCES CLIENTE(clie_dni))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla VENTA creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaClientes() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CLIENTE" 
				+"(clie_tipo VARCHAR(40),"
				+"clie_nombre VARCHAR(40),"
				+"clie_apellido VARCHAR(40),"
				+"clie_dni INTEGER(10) NOT NULL,"
				+"clie_telefono INTEGER(10),"
				+"clie_email VARCHAR(40),"
				+"dire_codPostal INTEGER(5),"
				+"dire_barrio VARCHAR(40),"
				+"dire_numero INTEGER(6),"
				+"dire_calle VARCHAR(40),"
				+"clie_como_llego VARCHAR(40),"
				+"PRIMARY KEY (clie_dni))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla CLIENTE creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public static void crearTablaProductos() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS PRODUCTO" 
				+ "(prod_id INTEGER auto_increment,"
				+"prod_nombre VARCHAR(255),"
				+"prod_kilos DOUBLE(10),"
				+"prod_stock INTEGER(10),"
				+"prod_precio DOUBLE(10),"
				+"prod_precio_mayor DOUBLE(10),"
				+"prod_costo DOUBLE(10),"
				+"prod_cantidad_mayor int(10),"
				+"PRIMARY KEY (prod_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla PRODUCTO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}
