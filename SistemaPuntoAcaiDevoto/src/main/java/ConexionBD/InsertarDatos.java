package ConexionBD;


import java.sql.SQLException;
import java.util.List;

import ModeloGasto.Gasto;
import ModelosClientes.Cliente;
import Productos.Producto;
import Ventas.Item;
import Ventas.Venta;

public class InsertarDatos extends ConexionBd{
	
	public InsertarDatos() throws SQLException {
		super();
	}
	
	
	public void insertarGasto(Gasto unGasto) {
		String sql = "insert into GASTO"
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
				+ "dire_calle,"
				+ "clie_como_llego,"
				+ "clie_rubro) "
				+ "values('"+unCliente.getTipo()+"','"+unCliente.getNombre()+"','"+unCliente.getApellido()+"','"+unCliente.getDni()+"','"+unCliente.getTelefono()+"','"+unCliente.getEmail()+"','"+unCliente.getDireccion().getCodPostal()+"','"+unCliente.getDireccion().getBarrio()+"','"+unCliente.getDireccion().getNumero()+"','"+unCliente.getDireccion().getCalle()+"','"+unCliente.getComoLlego()+"','"+unCliente.getRubro()+"')"; 
		ejecutarUpdate(sql, "Cliente ingresado");	
	}
	
	

	public void insertarProducto(Producto unProducto) {
		String sql = "insert into PRODUCTO(prod_nombre, prod_kilos, prod_stock, prod_precio, prod_precio_mayor, prod_costo) "
				+ "values('"+unProducto.getNombre()+"','"+unProducto.getKilos()+"','"+unProducto.getStock()+"','"+unProducto.getPrecioUnitario()+"','"+unProducto.getPrecioMayor()+"','"+unProducto.getCosto()+"')"; 
		
		ejecutarUpdate(sql, "Producto ingresado");
		
	}
	
	
	public void insertarVenta(Venta unaVenta) throws SQLException {
		String sql = "INSERT INTO VENTA"
				+ "(venta_cliente,"
				+ "venta_fecha,"
				+ "venta_precioTotal,"
				+ "venta_ganancia,"
				+ "venta_envio)"
				+ "values('"+unaVenta.getCliente().getDni()+"','"+unaVenta.getFecha()+"','"+unaVenta.getPrecioTotal()+"','"+unaVenta.getGanancia()+"','"+unaVenta.getEnvio()+"')";
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
	
	

}
