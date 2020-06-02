package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBd {
	
	Connection con;
	Statement stmt;
	public ConexionBd() throws SQLException {
		this.con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
		this.stmt= con.createStatement();
	}
	
	public void ejecutarUpdate(String sql, String mensaje) {
		try {
			stmt.executeUpdate(sql);
			System.out.println(mensaje);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet ejecutarQuery(String sql, Statement unStmt) throws SQLException {
		unStmt = con.createStatement();
		
		return unStmt.executeQuery(sql);
		
	}
	
	
	
	public void cerrarConexion() throws SQLException {
		con.close();
	}
	
}
