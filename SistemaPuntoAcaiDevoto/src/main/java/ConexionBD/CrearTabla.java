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
				+ "(id INTEGER auto_increment,"
				+"nombre VARCHAR(40),"
				+"apellido VARCHAR(40),"
				+"PRIMARY KEY (id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}
