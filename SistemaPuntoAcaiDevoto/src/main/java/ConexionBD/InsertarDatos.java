package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertarDatos extends RealizarConsulta{
	
	public InsertarDatos(String sqlQuery) {
		super(sqlQuery);
	}

	public void ejecutarConsulta() {
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
}
