package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModificarDatos {
	
	public void editarCliente(Cliente unCliente){
		ResultSet rs;
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "select * from CLIENTE";
			rs = stmt.executeQuery(sql);
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}
