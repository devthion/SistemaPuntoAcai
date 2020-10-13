package ConexionBD;

import java.sql.SQLException;
import java.time.LocalDate;

import Egresos.Egreso;
import Gastos.Gasto;
import ModeloInversion.IngresoDiario;
import ModeloInversion.Inversion;
import ModelosClientes.Cliente;
import Productos.Combo;
import Ventas.Venta;


public class ModificarDatos extends ConexionBd {
	
	public ModificarDatos() throws SQLException {
		super();
	}
	
	public void eliminarCombo(Combo combo) {
		String sql = "DELETE FROM COMBO "
				+ "WHERE combo_nombre= '"+combo.getCombo_nombre()+"'";
		ejecutarUpdate(sql, "Combo: "+combo.getCombo_nombre()+", eliminado");
	}
	
	public void actualizarPrecioCombo(double precioActualizado, String combo_nombre) {
		String sql="UPDATE COMBO SET "
				+"combo_precio= '"+precioActualizado+"'"
				+" WHERE combo_nombre= '"+combo_nombre+"'";
		ejecutarUpdate(sql, "COMBO actualizado");
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
	
	public void agregarCalleEnvio() {
		String sql = "ALTER TABLE Venta ADD venta_envio_calle varchar(255)";
		ejecutarUpdate(sql, "Columna envio venta agregada");
	}
	
	public void agregarNumeroEnvio() {
		String sql = "ALTER TABLE Venta ADD venta_envio_numero INTEGER(10)";
		ejecutarUpdate(sql, "Columna envio venta agregada");
	}
	
	public void agregarDptoEnvio() {
		String sql = "ALTER TABLE Venta ADD venta_envio_dpto varchar(40)";
		ejecutarUpdate(sql, "Columna envio venta agregada");
	}
	
	public void agregarBarrioEnvio() {
		String sql = "ALTER TABLE Venta ADD venta_envio_barrio varchar(255)";
		ejecutarUpdate(sql, "Columna envio venta agregada");
	}
	
	public void agregarDpto() {
		String sql = "ALTER TABLE Cliente ADD dire_dpto varchar(30)";
		ejecutarUpdate(sql, "Columna clie_dpto agregada");
	}
	
	public void agregarDeudaCliente() {
		String sql = "ALTER TABLE Cliente ADD clie_deuda DOUBLE(10)";
		ejecutarUpdate(sql, "Columna clie_deuda agregada");
	}
	
	public void inicializarDeudaCliente() {
		String sql = "UPDATE CLIENTE SET clie_deuda=0 WHERE clie_deuda = null ";
		ejecutarUpdate(sql, "Valores inicializados");
	}
	
	public void agregarTipoDeVenta() {
		String sql = "ALTER TABLE Venta ADD venta_tipo_de_pago varchar(255)";
		ejecutarUpdate(sql, "Columna venta_tipo_de_pago agregada");
	}
	
	public void cambiarNumeroTelefonico() {
		String sql = "ALTER TABLE CLIENTE ALTER COLUMN clie_telefono BIGINT(11) ";
		ejecutarUpdate(sql, "El telefono de la tabla ha sido modificada.");
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
					+ "dire_dpto = '"+clienteEditado.getDireccion().getDpto()+"',"
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
	
	
	public void actualizarPrecios(double precioUnitario, double precioMayor, double costo, int prod_id, int cantidad) {
		String sql="UPDATE PRODUCTO SET "
				+"prod_precio= '"+precioUnitario+"',"
				+"prod_precio_mayor='"+precioMayor+"',"
				+"prod_costo= '"+costo+"',"
				+"prod_cantidad_mayor= '"+cantidad+"'"
				+" WHERE prod_id= '"+prod_id+"'";
		ejecutarUpdate(sql, "Precios actualizados");
	}
	
	public void eliminarInversion(int id) {
		String sqlString = "DELETE FROM INVERSION "
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

	
	public void eliminarPropina(int id) {
		String sqlString = "DELETE FROM PROPINA "
				+ "WHERE propina_id = '"+id+"'";
		ejecutarUpdate(sqlString, "propina eliminated");
				
	}

	public void cambiarDeuda(int dni, double deuda) {
		String sql ="UPDATE CLIENTE SET "
				+"clie_deuda= '"+deuda+"'"
				+" WHERE clie_dni= '"+dni+"'";
		ejecutarUpdate(sql, "Deuda actualizada para: " +dni);
		
	}

	public void editarEgreso(int id, Egreso egresoModificado) {
		String sql = "UPDATE EGRESO SET "
				+ "egreso_fecha = '"+egresoModificado.getFecha()+"',"
				+"egreso_monto = '"+egresoModificado.getMonto() +"',"
				+ "egreso_detalle = '"+egresoModificado.getDetalle()+"'"
						+ "WHERE egreso_id = '"+id+"'";
		ejecutarUpdate(sql, "EGRESO EDITADO");
		
	}

	public void eliminarEgreso(int id) {
		String sqlString = "DELETE FROM EGRESO "
				+ "WHERE egreso_id = '"+id+"'";
		ejecutarUpdate(sqlString, "egreso eliminated");
		
	}

	public void editarIngresoDiario(int id, IngresoDiario ingresoModificado) {
		String sql = "UPDATE INGRESO_DIARIO SET "
				+ "ingreso_fecha = '"+ingresoModificado.getFecha()+"',"
				+"ingreso_monto = '"+ingresoModificado.getMonto() +"',"
				+ "ingreso_detalle = '"+ingresoModificado.getDetalle()+"'"
						+ "WHERE ingreso_id = '"+id+"'";
		ejecutarUpdate(sql, "EGRESO EDITADO");
		
	}

	public void eliminarIngresoDiario(int id) {
		String sqlString = "DELETE FROM INGRESO_DIARIO "
				+ "WHERE ingreso_id = '"+id+"'";
		ejecutarUpdate(sqlString, "Ingreso Diario eliminated");
		
	}

	public void actualizarNombre(int prod_id, String nombre_nuevo) {
		String sql ="UPDATE PRODUCTO SET "
				+"prod_nombre= '"+nombre_nuevo+"'"
				+" WHERE prod_id= '"+prod_id+"'";
		ejecutarUpdate(sql, "Nombre actualizado para: " +prod_id);
		
	}


}
