package ConexionBD;

import java.sql.SQLException;
import java.time.LocalDate;

import ModeloGasto.Gasto;
import ModelosClientes.Cliente;
import Ventas.Venta;


public class ModificarDatos extends ConexionBd {
	
	public ModificarDatos() throws SQLException {
		super();
	}
	
	public void eliminarVenta(Venta unaVenta) {
		eliminarItemsDeUnaVenta(unaVenta.getVenta_id());
		String sql = "DELETE FROM VENTA "
				+ "WHERE venta_id= '"+unaVenta.getVenta_id()+"'";
		ejecutarUpdate(sql, "Venta: "+unaVenta.getVenta_id()+", eliminada");
	}
	
	public void cambiarDirecciones() {
		String sql = "ALTER TABLE CLIENTE ALTER COLUMN dire_calle VARCHAR(255) ";
		ejecutarUpdate(sql, "La Direccion de la tabla ha sido modificada.");
	}
	
	
	
	public void eliminarCajaCerrada(LocalDate unaFecha) {
		String sql= "DELETE FROM CAJACERRADA "
				+ "WHERE caja_cerrada_fecha = '"+unaFecha+"'";
		ejecutarUpdate(sql, "CAJACERRADA DE LA FECHA "+unaFecha+", ELIMINADA");
	}
	
	public void eliminarItemsDeUnaVenta(int ventaid) {
		String sql ="DELETE FROM ITEM_VENTA "
				+ "WHERE item_venta = '"+ventaid+"'";
		ejecutarUpdate(sql,"Items de venta: "+ventaid+", eliminados");
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
	
	public void editarGasto(Gasto gastoViejo, Gasto gastoNuevo){
		
		String sql = "UPDATE GASTO SET "
				+ "gasto_fecha = '"+gastoNuevo.getFecha()+"',"
				+"gasto_monto = '"+gastoNuevo.getMonto() +"',"
				+ "gasto_detalle = '"+gastoNuevo.getDetalle()+"'"
						+ "WHERE gasto_fecha = '"+gastoViejo.getFecha()+"' AND gasto_monto ='"+gastoViejo.getMonto()+"' AND  gasto_detalle ='"+gastoViejo.getDetalle()+"'";
		ejecutarUpdate(sql, "GASTO EDITADO");
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
