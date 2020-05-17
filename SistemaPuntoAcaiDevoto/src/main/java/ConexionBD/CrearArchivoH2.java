package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CrearArchivoH2 {
	
	public void crearArchivo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
		System.out.println("Database creada");
	}
	
	
}
