package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CrearArchivoH2 {
	
	public static void main(String[]args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		crearArchivo();	
	}
	
	public static void crearArchivo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
		System.out.println("Database creada");
	}
	
	
}
