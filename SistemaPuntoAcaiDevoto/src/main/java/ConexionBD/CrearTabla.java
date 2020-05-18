package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearTabla {

	public static void main(String[]args) {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CLIENTE" 
				+ "(clie_id INTEGER auto_increment,"
				+"clie_tipo BOOLEAN,"
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
			System.out.println("Tabla creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}
