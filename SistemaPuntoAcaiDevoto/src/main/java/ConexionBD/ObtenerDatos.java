package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ObtenerDatos {


	public ResultSet ejecutarConsulta(){
		ResultSet rs = null;
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			//String sql = "select * from CLIENTE";
			rs = stmt.executeQuery(sql);
			/*while(rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}*/
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return rs;
	}
}
