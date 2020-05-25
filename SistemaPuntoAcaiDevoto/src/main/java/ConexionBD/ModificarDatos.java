package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModificarDatos extends ConexionBd {
	
	public ModificarDatos() throws SQLException {
		super();
	}

	public void editarCliente(int dniCliente, Cliente clienteEditado){
	
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
			ejecutarUpdate(sql, "cliente editado");
	}

}
