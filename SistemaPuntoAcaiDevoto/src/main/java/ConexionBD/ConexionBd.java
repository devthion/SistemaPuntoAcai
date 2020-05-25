package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBd {
	
	Connection con;
	Statement stmt;
	public ConexionBd() throws SQLException {
		this.con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
		this.stmt= con.createStatement();
	}
	
}
