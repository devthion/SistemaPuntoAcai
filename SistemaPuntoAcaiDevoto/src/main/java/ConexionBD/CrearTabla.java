package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearTabla {

	public static void main(String[]args) {
		crearTablaProductos();
		crearTablaClientes();
	}
	
	public static void crearTablaClientes() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CLIENTE" 
				+ "(clie_id INTEGER auto_increment,"
				+"clie_tipo VARCHAR(40),"
				+"clie_nombre VARCHAR(40),"
				+"clie_apellido VARCHAR(40),"
				+"clie_dni INTEGER(10),"
				+"clie_telefono INTEGER(10),"
				+"clie_email VARCHAR(40),"
				+"dire_codPostal INTEGER(5),"
				+"dire_barrio VARCHAR(40),"
				+"dire_numero INTEGER(6),"
				+"dire_calle VARCHAR(40),"
				+"clie_como_llego VARCHAR(40),"
				+"PRIMARY KEY (clie_id))";
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
