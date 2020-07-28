package ConexionBD;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import Gastos.Gasto;
import Gastos.GastosDiarios;
import Gastos.GastosGenerales;
import Gastos.GastosProductos;

import ModeloInversion.Inversion;
import ModelosClientes.Cliente;
import Propina.Propina;
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
	
	
	//ESTAs VUELAn MEPA---------------
	public void cambiarDirecciones() {
		String sql = "ALTER TABLE CLIENTE ALTER COLUMN dire_calle VARCHAR(255) ";
		ejecutarUpdate(sql, "La Direccion de la tabla ha sido modificada.");
	}
	
	public void agregarObservacion() {
		String sql = "ALTER TABLE Venta ADD venta_observacion varchar(255)";
		ejecutarUpdate(sql, "Tabla observacion agregada");
	}
	//---------------------------
	
	
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
					+ "clie_rubro = '"+clienteEditado.getRubro()+"',"
					+ "clie_dni = '"+clienteEditado.getDni()+"'"
							+ "WHERE clie_dni = '"+dniCliente+"'";
			ejecutarUpdate(sql, "cliente editado");
	}

	
	public void editarGasto(int idGasto, Gasto gastoNuevo, String tipoGasto){
		
		String sql = "UPDATE "+tipoGasto+" SET "
				+ "gasto_fecha = '"+gastoNuevo.getFecha()+"',"
				+"gasto_monto = '"+gastoNuevo.getMonto() +"',"
				+ "gasto_detalle = '"+gastoNuevo.getDetalle()+"'"
						+ "WHERE gasto_id= '"+idGasto+"'";
		ejecutarUpdate(sql, "GASTO EDITADO");
	}
	

	
	public void eliminarGasto(int id, String tipoGasto){
		
		String sql = "DELETE FROM "+tipoGasto+" "
						+ "WHERE gasto_id = '"+id+"'";
		ejecutarUpdate(sql, "GASTO ELIMINADO");
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
	
	public void eliminarInversion(int id) {
		String sqlString = "DELETE FROM INVERSION"
				+ "WHERE inver_id = '"+id+"'";
		ejecutarUpdate(sqlString, "inversion eliminated");
	}

	public void editarInversion(int id, Inversion inversionNuevo) {
		String sql = "UPDATE INVERSION SET "
				+ "inver_fecha = '"+inversionNuevo.getFecha()+"',"
				+"inver_monto = '"+inversionNuevo.getMonto() +"',"
				+ "inver_detalle = '"+inversionNuevo.getDetalle()+"'"
						+ "WHERE inver_id = '"+id+"'";
		ejecutarUpdate(sql, "INVERSION EDITADA");
		
	}

	public void modificarPropina(Propina propinaModificada) {
		String sql = "UPDATE PROPINA SET"
				+ "propina_monto = propina_monto + '"+propinaModificada.getMonto()+"'"
						+ "propina_fecha = '"+propinaModificada.getFecha()+"'"
				+ "WHERE propina_fecha = '"+propinaModificada.getId()+"'";
		ejecutarUpdate(sql, "Propina actualizada");
	}
	
	public void eliminarPropina(int id) {
	
		String sqlString = "DELETE FROM PROPINA"
				+ "WHERE propina_id = '"+id+"'";
		ejecutarUpdate(sqlString, "propina eliminated");
				
	}


}
