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
	
	public void editarCliente(int dniCliente, Cliente clienteEditado){
		
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/my", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "UPDATE CLIENTE SET "
					+ "clie_tipo = '"+clienteEditado.getTipo()+"',"
					+ "clie_nombre = '"+clienteEditado.getNombre()+"',"
					+ "clie_apellido = '"+clienteEditado.getApellido()+"',"
					+ "clie_telefono = '"+clienteEditado.getTelefono()+"',"
					+ "clie_email= '"+clienteEditado.getEmail()+"',"
					+ "dire_codPostal= '"+clienteEditado.getCodPostal()+"',"
					+ "dire_barrio= '"+clienteEditado.getBarrio()+"',"
					+ "dire_numero = '"+clienteEditado.getNumero()+"',"
					+ "dire_calle= '"+clienteEditado.getCalle()+"',"
					+ "clie_como_llego = '"+clienteEditado.getComoLlego()+"'"
							+ "WHERE clie_dni = '"+dniCliente+"'";
			stmt.executeUpdate(sql);
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}
