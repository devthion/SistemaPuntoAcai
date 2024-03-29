package ConexionBD;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import Egresos.Egreso;
import Gastos.Gasto;
import ModeloInversion.IngresoDiario;
import ModeloInversion.Inversion;
import ModelosClientes.Cliente;
import Productos.Combo;
import Productos.Producto;
import Propina.Propina;
import Ventas.CajaCerrada;
import Ventas.Item;
import Ventas.Venta;

public class InsertarDatos extends ConexionBd{
	
	public InsertarDatos() throws SQLException {
		super();
	}
	
	public void insertarDispositivo(int dispositivoId, int valor) { //para impresora el id es 1...
		String sql = "INSERT INTO DISPOSITIVO"
				+ "(dispositivo_id,"
				+ "dispositivo_valor)"
				+ "values('"+dispositivoId+"','"+valor+"')";
		ejecutarUpdate(sql, "Dispositivo Ingresado");
	}
	
	public void insertarPropina(Propina unaPropina) {
		String sql = "INSERT INTO PROPINA"
				+ "(propina_fecha,"
				+ "propina_monto)"
				+ "values('"+unaPropina.getFecha()+"','"+unaPropina.getMonto()+"')";
		ejecutarUpdate(sql, "Propina Ingresada");
	}
	
	public void insertarCajaCerrada(CajaCerrada unaCajaCerrada) {
		String sql="INSERT INTO CAJACERRADA"
				+ "(caja_cerrada_fecha,"
				+ "caja_cerrada_monto_real,"
				+ "caja_cerrada_monto_ideal)"
				+ "values('"+unaCajaCerrada.getFecha()+"','"+unaCajaCerrada.getMonto_real()+"','"+unaCajaCerrada.getMonto_ideal()+"')";
		ejecutarUpdate(sql, "Caja Cerrada");
	}
	
	public void insertarGasto(Gasto unGasto, String tipoGasto) {
		String sql = "insert into "+tipoGasto+" "
				+ "(gasto_fecha,"
				+ "gasto_monto,"
				+ "gasto_detalle)"
				+ "values('"+unGasto.getFecha()+"','"+unGasto.getMonto()+"','"+unGasto.getDetalle()+"')";
		ejecutarUpdate(sql, "Gasto ingresado");
				
	}
	
	public void insertarCliente(Cliente unCliente) {
		String sql = "insert into CLIENTE"
				+ "(clie_tipo,"
				+ "clie_nombre, "
				+ "clie_apellido, "
				+ "clie_dni, "
				+ "clie_telefono, "
				+ "clie_email, "
				+ "dire_codPostal, "
				+ "dire_barrio,"
				+ "dire_numero,"
				+ "dire_dpto,"
				+ "dire_calle,"
				+ "clie_como_llego,"
				+ "clie_rubro) "
				+ "values('"+unCliente.getTipo()+"','"+unCliente.getNombre()+"','"+unCliente.getApellido()+"','"+unCliente.getDni()+"','"+unCliente.getTelefono()+"','"+unCliente.getEmail()+"','"+unCliente.getDireccion().getCodPostal()+"','"+unCliente.getDireccion().getBarrio()+"','"+unCliente.getDireccion().getNumero()+"','"+unCliente.getDireccion().getDpto()+"','"+unCliente.getDireccion().getCalle()+"','"+unCliente.getComoLlego()+"','"+unCliente.getRubro()+"')"; 
		ejecutarUpdate(sql, "Cliente ingresado");	
		System.out.println(unCliente.getDpto());
	}
	
	public void insertarCombo(Combo combo) {
		String sql = "insert into COMBO"
				+ "(combo_nombre,"
				+ "combo_precio)"
				+ "values('"+combo.getCombo_nombre()+"','"+combo.getCombo_precio()+"')"; 
		ejecutarUpdate(sql, "Combo ingresado");	
	}
	

	public void insertarProducto(Producto unProducto) {
		String sql = "insert into PRODUCTO(prod_nombre, prod_kilos, prod_stock, prod_precio, prod_precio_mayor, prod_costo, prod_cantidad_mayor) "
				+ "values('"+unProducto.getNombre()+"','"+unProducto.getKilos()+"','"+unProducto.getStock()+"','"+unProducto.getPrecioUnitario()+"','"+unProducto.getPrecioMayor()+"','"+unProducto.getCosto()+"','"+unProducto.getCantidadPorMayor()+"')"; 
		
		ejecutarUpdate(sql, "Producto ingresado");
		
	}
	
	
	public void insertarVenta(Venta unaVenta) throws SQLException {
		
		boolean estado;
		if((!Objects.isNull(unaVenta.getUnEnvio())) && unaVenta.getUnEnvio().getFechaEntrega().isBefore(LocalDate.now())) {
			estado = true;
		}else {
			estado = false;
		}
		System.out.println(unaVenta.getTipoDePago());
		System.out.println(unaVenta.getUnEnvio().getPrecio());
		
		String sql = "INSERT INTO VENTA"
				+ "(venta_cliente,"
				+ "venta_fecha,"
				+ "venta_precioTotal,"
				+ "venta_ganancia,"
				+ "venta_envio_precio,"
				+ "venta_estado_envio,"
				+ "venta_horario_envio,"
				+ "venta_fecha_entrega,"
				+ "venta_tipo_de_pago,"
				+ "venta_observacion,"	
				+ "venta_envio_calle,"
				+ "venta_envio_numero,"
				+ "venta_envio_dpto,"
				+ "venta_envio_barrio)"
				+ "values('"+unaVenta.getCliente().getDni()+"','"+unaVenta.getFecha()+"','"+unaVenta.getPrecioTotal()+"','"+unaVenta.getGanancia()+"','"+unaVenta.getEnvioPrecio()+"','"+estado+"','"+unaVenta.getHorario()+"','"+unaVenta.getFechaEntrega()+"','"+unaVenta.getTipoDePago()
				+"','"+unaVenta.getObservacion()+"','"+unaVenta.getUnEnvio().getDireccion().getCalle()+"','"+unaVenta.getUnEnvio().getDireccion().getNumero()+"','"+unaVenta.getUnEnvio().getDireccion().getDpto()+"','"+unaVenta.getUnEnvio().getDireccion().getBarrio()+"')";
		ejecutarUpdate(sql, "Venta ingresada");
		cerrarConexion();

		unaVenta.getItems().stream().forEach(unItem-> {
			try {
				unItem.almacenarItemVenta(new ObtenerDatos().obtenerIdUltimaVentaIngresada());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void insertarItemVenta(Item unItem,int venta_id) {
		String sql = "INSERT INTO ITEM_VENTA"
						+ "(item_producto,"
						+ "item_venta,"
						+ "item_cantidad,"
						+ "item_precio)"
						+ "values('"+unItem.getProdId()+"','"+venta_id+"','"+unItem.getCantidad()+"','"+unItem.getPrecioFinal()+"')";
		ejecutarUpdate(sql, "Item ingresado");
				
		
	}

	public void concretarVenta(Venta venta) {
		LocalDate fechaEntrega;
		if(venta.getUnEnvio().getFechaEntrega().isBefore(LocalDate.now())) {
			fechaEntrega = venta.getUnEnvio().getFechaEntrega();
		}else {
			fechaEntrega =LocalDate.now();
		}
		
		String sql= "UPDATE VENTA "
				+ "SET venta_estado_envio='"+true+"', venta_fecha_entrega = '"+fechaEntrega+"'"
						+ "WHERE venta_id='"+venta.getVenta_id()+"'";
		ejecutarUpdate(sql,"Venta '"+venta.getVenta_id()+"' concretada");
	}

	public void insertarInversion(Inversion inversion) {
		String sql = "insert into INVERSION"
				+ "(inver_fecha,"
				+ "inver_monto,"
				+ "inver_detalle)"
				+ "values('"+inversion.getFecha()+"','"+inversion.getMonto()+"','"+inversion.getDetalle()+"')";
		ejecutarUpdate(sql, "Inversion ingresada");
		
	}

	public void insertarEgreso(Egreso egreso) {
		String sql = "insert into EGRESO"
				+ "(egreso_fecha,"
				+ "egreso_monto,"
				+ "egreso_detalle)"
				+ "values('"+egreso.getFecha()+"','"+egreso.getMonto()+"','"+egreso.getDetalle()+"')";
		ejecutarUpdate(sql, "Egreso ingresado");
		
	}

	public void insertarIngresoDiario(IngresoDiario ingresoDiario) {
		String sql = "insert into INGRESO_DIARIO"
				+ "(ingreso_fecha,"
				+ "ingreso_monto,"
				+ "ingreso_detalle)"
				+ "values('"+ingresoDiario.getFecha()+"','"+ingresoDiario.getMonto()+"','"+ingresoDiario.getDetalle()+"')";
		ejecutarUpdate(sql, "Ingreso Diario ingresado");
		
	}

	public void actualizarDispositivo(int dispositivoId, int valor) {
		String sql= "UPDATE DISPOSITIVO "
				+ "SET dispositivo_valor='"+valor+"'"
						+ "WHERE dispositivo_id='"+dispositivoId+"'";
		ejecutarUpdate(sql,"Dispositivo Actualizado");
		
	}


	

}
