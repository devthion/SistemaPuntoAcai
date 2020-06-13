package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ModeloGasto.Gasto;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import ModelosGraficos.ClientesPorBarrio;
import ModelosGraficos.ComoLlegoUnCliente;
import ModelosGraficos.VentasPorMes;
import Productos.Producto;
import Ventas.CajaCerrada;
import Ventas.Envio;
import Ventas.Item;
import Ventas.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObtenerDatos extends ConexionBd{
	ResultSet rs;
	ResultSet rsUnaVenta;
	ResultSet rsUnProd;
	ResultSet rsUnItem;
	ResultSet rsUnCliente;
	String sql;

	public ObtenerDatos() throws SQLException {
		super();
	}
	
	public List<CajaCerrada> obtenerCajasCerradas() throws SQLException{
		List<CajaCerrada> cajasCerradas = new ArrayList<>();
		ResultSet rs;
		Statement unStmt = null;
		
		sql="SELECT * FROM CAJACERRADA";
		rs=ejecutarQuery(sql, unStmt);
		while(rs.next()) {
			LocalDate fecha = rs.getDate(1).toLocalDate();
			double monto_real = rs.getDouble(2);
			double monto_ideal = rs.getDouble(3);
			
			CajaCerrada unaCaja = new CajaCerrada(fecha,monto_real,monto_ideal);
			cajasCerradas.add(unaCaja);
		}
		return cajasCerradas;
		
	}
	
	public List<VentasPorMes> obtenerVentasPorMes() throws SQLException{
		List<VentasPorMes> ventasPorMes = new ArrayList<VentasPorMes>();
		ResultSet rs;
		Statement unStmt = null;
		
		sql="SELECT MONTH(venta_fecha),isnull(COUNT(venta_fecha),0) FROM VENTA "
				+ "WHERE YEAR(venta_fecha)= '"+LocalDate.now().getYear()+"'"
						+ "GROUP BY MONTH(venta_fecha)";
		rs=ejecutarQuery(sql, unStmt);
		while(rs.next()) {
			VentasPorMes unMesConVentas = new VentasPorMes(rs.getInt(1), rs.getInt(2));
			ventasPorMes.add(unMesConVentas);
		}
		return ventasPorMes;
	}
	
	
	public ObservableList<Gasto> obtenerGastos() throws SQLException{
		ResultSet rs;
		Statement unStmt =null;
		ObservableList<Gasto> gastos = FXCollections.observableArrayList();	
		
		sql="select * from GASTO";
		rs=ejecutarQuery(sql,unStmt);
		while(rs.next()) {
			LocalDate date = rs.getDate(2).toLocalDate();
			Gasto unGasto = new Gasto(rs.getString(4),rs.getDouble(3));
			unGasto.setFecha(date);
			gastos.add(unGasto);
		}
		return gastos;
	}

	public ObservableList<Cliente> obtenerClientes(String sql) throws SQLException {

		Statement unStmt = null;
		ObservableList<Cliente> clientes = FXCollections.observableArrayList();
		
	
		//sql = "select * from CLIENTE";
		rs = ejecutarQuery(sql,unStmt);
		while(rs.next()) {
			Direccion unaDireccion= new Direccion(rs.getString(10),rs.getInt(9),rs.getString(8),rs.getInt(7));
			Cliente unCliente = new Cliente(rs.getInt(4),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(6),unaDireccion,rs.getString(1),rs.getString(11), rs.getString(12));
			clientes.add(unCliente);
		}
		
		return clientes;
	}
	
	
	
	
	public double ingresosGeneradosPor(int clie_dni) throws SQLException {
		Statement unStmt = null;
		int ingresos= 0;
		 sql = "SELECT CASE WHEN '"+clie_dni+"' IN (SELECT venta_cliente FROM VENTA) THEN "
				+ "(SELECT SUM(venta_precioTotal)FROM VENTA WHERE venta_cliente = '"+clie_dni+"') "
				+ "ELSE 0 END";
		rs=ejecutarQuery(sql,unStmt);
		
		while(rs.next()) {
			ingresos=rs.getInt(1);
		}
		
			return ingresos;
		
		
	}
	
	public ObservableList<Producto> obtenerProductos() throws SQLException{
		Statement unStmt = null;
		ObservableList<Producto> productos = FXCollections.observableArrayList();
		
		sql="SELECT * FROM PRODUCTO";
		rs=ejecutarQuery(sql,unStmt);
		while(rs.next()) {
			Producto unProducto = new Producto(rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7), rs.getInt(8));
			unProducto.setProd_id(rs.getInt(1));
			productos.add(unProducto);
		}
		return productos;
		
	}
	
	public List<ComoLlegoUnCliente> obtenerComoLlegaronLosClientes() throws SQLException{
		Statement unStmt = null;
		ResultSet rs;
		List<ComoLlegoUnCliente> comoLlegaronLosClientes = new ArrayList<ComoLlegoUnCliente>();
		sql="SELECT clie_como_llego, COUNT(clie_como_llego) FROM CLIENTE "
				+ "GROUP BY clie_como_llego";
		rs=ejecutarQuery(sql, unStmt);
		while(rs.next()) {
			ComoLlegoUnCliente comoLlegoUnCliente = new ComoLlegoUnCliente(rs.getString(1),rs.getInt(2));
			comoLlegaronLosClientes.add(comoLlegoUnCliente);
		}
		return comoLlegaronLosClientes;
	}
	
	
	public List<ClientesPorBarrio> obtenerClientesPorBarrio() throws SQLException{
		Statement unStmt = null;
		List<ClientesPorBarrio> clientesPorBarrioList = new ArrayList<ClientesPorBarrio>();
		sql="SELECT dire_barrio,COUNT(dire_barrio) FROM CLIENTE "
				+ "GROUP BY dire_barrio";
		rs=ejecutarQuery(sql,unStmt);
		while(rs.next()) {
			ClientesPorBarrio clientesPorBarrio = new ClientesPorBarrio(rs.getString(1), rs.getInt(2));
			clientesPorBarrioList.add(clientesPorBarrio);
		}
	
		return clientesPorBarrioList;
	}
	
	public int obtenerIdUltimaVentaIngresada() throws SQLException {
		Statement unStmt = null;
		int id = 0;
		sql="SELECT top 1 venta_id FROM VENTA ORDER BY venta_id desc";
		rs=ejecutarQuery(sql, unStmt);
		while(rs.next()) {
			id=rs.getInt(1);
		}
		return id;
	}
	public ObservableList<Venta> obtenerVentasPendientes() throws SQLException{
		Statement unStmt = null;
		ObservableList<Venta> ventas = FXCollections.observableArrayList();
		Venta unaVenta;
		sql = "SELECT * FROM VENTA WHERE venta_estado_envio ='"+false+"'";
		rsUnaVenta=ejecutarQuery(sql,unStmt);
		while(rsUnaVenta.next()) {
			int ventaId=rsUnaVenta.getInt(1);
		
			LocalDate date = rsUnaVenta.getDate(3).toLocalDate();
			
			int ventaCliente = rsUnaVenta.getInt(2);
			double ganancia = rsUnaVenta.getDouble(5);
			double precioTotal = rsUnaVenta.getDouble(4);
			double precio_envio = rsUnaVenta.getDouble(6);
			boolean estado_entrega = rsUnaVenta.getBoolean(7);
			String horario_entrega = rsUnaVenta.getString(8);
			LocalDate fecha_entrega = rsUnaVenta.getDate(9).toLocalDate();
			
			unaVenta = new Venta(obtenerUnCliente(ventaCliente),date,itemsDeVenta(ventaId));
			unaVenta.setVenta_ganancia(ganancia);
			unaVenta.setVenta_precioTotal(precioTotal);
			
			Envio unEnvio = new Envio(horario_entrega,precio_envio,fecha_entrega);
			unEnvio.setEstado(estado_entrega);
			unaVenta.setEnvio(unEnvio);
			unaVenta.setVentaId(ventaId);
			ventas.add(unaVenta);
			}
		return ventas;
	}
	
	public ObservableList<Venta> obtenerVentas() throws SQLException{
		Statement unStmt = null;
		ObservableList<Venta> ventas = FXCollections.observableArrayList();
		Venta unaVenta;
		sql = "SELECT * FROM VENTA";
		rsUnaVenta=ejecutarQuery(sql,unStmt);
		while(rsUnaVenta.next()) {
			int ventaId=rsUnaVenta.getInt(1);
		
			LocalDate date = rsUnaVenta.getDate(3).toLocalDate();
			
			int ventaCliente = rsUnaVenta.getInt(2);
			double ganancia = rsUnaVenta.getDouble(5);
			double precioTotal = rsUnaVenta.getDouble(4);
			double precio_envio = rsUnaVenta.getDouble(6);
			boolean estado_entrega = rsUnaVenta.getBoolean(7);
			String horario_entrega = rsUnaVenta.getString(8);
			LocalDate fecha_entrega = rsUnaVenta.getDate(9).toLocalDate();
			
			unaVenta = new Venta(obtenerUnCliente(ventaCliente),date,itemsDeVenta(ventaId));
			unaVenta.setVenta_ganancia(ganancia);
			unaVenta.setVenta_precioTotal(precioTotal);
			
			Envio unEnvio = new Envio(horario_entrega,precio_envio,fecha_entrega);
			unEnvio.setEstado(estado_entrega);
			unaVenta.setEnvio(unEnvio);
			ventas.add(unaVenta);
			}
		return ventas;
	}
	
	private Cliente obtenerUnCliente(int clie_dni) throws SQLException {
		Statement unStmt = null;
		Cliente unCliente = null;
		Direccion unaDireccion = null;
		sql = "SELECT * FROM CLIENTE "
				+ "WHERE clie_dni = '"+clie_dni+"'";
		rsUnCliente=ejecutarQuery(sql,unStmt);
		while(rsUnCliente.next()) {
			unaDireccion = new Direccion(rsUnCliente.getString(10),rsUnCliente.getInt(9),rsUnCliente.getString(8),rsUnCliente.getInt(7));
			unCliente = new Cliente(rsUnCliente.getInt(4),rsUnCliente.getString(2),rsUnCliente.getString(3),rsUnCliente.getInt(5),rsUnCliente.getString(6),unaDireccion,rsUnCliente.getString(1),rsUnCliente.getString(11), rsUnCliente.getString(12));
		}
		return unCliente;
	}

	public List<Item> itemsDeVenta(int venta_id) throws SQLException{
		Statement unStmt = null;
		List<Item> itemsDeVenta = new ArrayList<Item>();
		Item unItem;
		sql ="SELECT * FROM ITEM_VENTA "
				+ "WHERE item_venta = '"+venta_id+"'";
		rsUnItem=ejecutarQuery(sql,unStmt);
		while(rsUnItem.next()) {
			int itemProducto = rsUnItem.getInt(1);
			Producto unProducto = obtenerProductoPorId(rsUnItem.getInt(1));
			
			
			unItem = new Item(unProducto, rsUnItem.getInt(3));
			unItem.setItemPrecio(rsUnItem.getDouble(4));
			itemsDeVenta.add(unItem);
		}
		return itemsDeVenta;
	}

	private Producto obtenerProductoPorId(int itemProducto) throws SQLException {
		Statement unStmt = null;
		Producto unProducto = null;
		sql = "SELECT * FROM PRODUCTO "
				+ "WHERE prod_id = '"+itemProducto+"'";
		rsUnProd=ejecutarQuery(sql,unStmt);
		while(rsUnProd.next()) {
			 unProducto = new Producto(rsUnProd.getString(2),rsUnProd.getDouble(3),rsUnProd.getInt(4),rsUnProd.getDouble(5),rsUnProd.getDouble(6),rsUnProd.getDouble(7), rsUnProd.getInt(8));
			 unProducto.setProd_id(rsUnProd.getInt(1));
		}
		return unProducto;
	}	

	
	
	public List<Item> obtenerItemsVenta() throws SQLException{
		Statement unStmt = null;
		List<Item> itemsDeVenta = new ArrayList<Item>();
		Item unItem;
		sql ="SELECT * FROM ITEM_VENTA ";
		rsUnItem=ejecutarQuery(sql,unStmt);
		while(rsUnItem.next()) {
			int itemProducto = rsUnItem.getInt(1);
			Producto unProducto = obtenerProductoPorId(rsUnItem.getInt(1));
			
			unItem = new Item(unProducto, rsUnItem.getInt(3));
			unItem.setItem_venta(rsUnItem.getInt(2));
			unItem.setItemPrecio(rsUnItem.getDouble(4));
			itemsDeVenta.add(unItem);
		}
		return itemsDeVenta;
	}

	public int obtenerCantidadTotalVendida(int prod_id) throws SQLException {
		Statement unStmt = null;
		ResultSet rs;
		int cantidadVendida=0;
		sql="SELECT sum(item_producto*item_cantidad) FROM ITEM_VENTA "
				+ "WHERE item_producto = '"+prod_id+"'"
						+ "GROUP BY item_producto";
		
		rs=ejecutarQuery(sql, unStmt);
			
		while(rs.next()) {
			cantidadVendida=rs.getInt(1);
		}	
		
		return cantidadVendida;
	}
	
	
}
