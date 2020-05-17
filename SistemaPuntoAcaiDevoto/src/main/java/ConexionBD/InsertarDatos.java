package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertarDatos {

	public static void main(String[]args) {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "insert into CLIENTE(nombre, apellido) values('Diego', 'vivona')"; 
			stmt.executeUpdate(sql);
			System.out.println("Datos ingresados");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
