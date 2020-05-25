package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObtenerDatos extends ConexionBd{


	public ObtenerDatos() throws SQLException {
		super();
	}

	public ObservableList<Cliente> obtenerClientes() throws SQLException {
		ObservableList<Cliente> clientes = FXCollections.observableArrayList();
		ResultSet rs;
	
			String sql = "select * from CLIENTE";
			rs = ejecutarQuery(sql);
			while(rs.next()) {
				Direccion unaDireccion= new Direccion(rs.getString(11),rs.getInt(10),rs.getString(9),rs.getInt(8));
				Cliente unCliente = new Cliente(rs.getInt(5),rs.getString(3),rs.getString(4),rs.getInt(6),rs.getString(7),unaDireccion,rs.getString(2),rs.getString(12),ingresosGeneradosPor(rs.getInt(1)));
				clientes.add(unCliente);
			}
		return clientes;
	}
	
	
	public double ingresosGeneradosPor(int unId) {
		return 1;
	}
}
