package ConexionBD;

import java.sql.SQLException;
import ModelosClientes.Cliente;


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
					+ "clie_como_llego = '"+clienteEditado.getComoLlego()+"',"
					+ "clie_dni = '"+clienteEditado.getDni()+"'"
							+ "WHERE clie_dni = '"+dniCliente+"'";
			ejecutarUpdate(sql, "cliente editado");
	}
	
	
	public void actualizarStock(int prod_id, int cantidad) {
		String sql ="UPDATE PRODUCTO SET "
				+"prod_stock= '"+cantidad+"'"
				+" WHERE prod_id= '"+prod_id+"'";
		ejecutarUpdate(sql, "stock actualizado para: " +prod_id);
	}
	
	
	public void actualizarPrecios(double precioUnitario, double precioMayor, double costo, int prod_id) {
		String sql="UPDATE PRODUCTO SET "
				+"prod_precio= '"+precioUnitario+"',"
				+"prod_precio_mayor='"+precioMayor+"',"
				+"prod_costo= '"+costo+"'"
				+" WHERE prod_id= '"+prod_id+"'";
		ejecutarUpdate(sql, "Precios actualizados");
	}

}
