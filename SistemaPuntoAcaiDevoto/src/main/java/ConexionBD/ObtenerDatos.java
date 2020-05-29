package ConexionBD;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import ModelosGraficos.ClientesPorBarrio;
import Productos.Producto;
import Ventas.Item;
import Ventas.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObtenerDatos extends ConexionBd{
	ResultSet rs;
	ResultSet rc;
	ResultSet rl;
	ResultSet rd;
	String sql;

	public ObtenerDatos() throws SQLException {
		super();
	}

	public ObservableList<Cliente> obtenerClientes() throws SQLException {
		ObservableList<Cliente> clientes = FXCollections.observableArrayList();
		
		sql = "select * from CLIENTE";
		rs = ejecutarQuery(sql);
		while(rs.next()) {
			Direccion unaDireccion= new Direccion(rs.getString(10),rs.getInt(9),rs.getString(8),rs.getInt(7));
			Cliente unCliente = new Cliente(rs.getInt(4),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(6),unaDireccion,rs.getString(1),rs.getString(11));
			clientes.add(unCliente);
		}
		return clientes;
	}
	
	
	public double ingresosGeneradosPor(int clie_dni) throws SQLException {
		int ingresos= 0;
		 sql = "SELECT CASE WHEN '"+clie_dni+"' IN (SELECT venta_cliente FROM VENTA) THEN "
				+ "(SELECT SUM(venta_precioTotal)FROM VENTA WHERE venta_cliente = '"+clie_dni+"') "
				+ "ELSE 0 END";
		rs=ejecutarQuery(sql);
		
		while(rs.next()) {
			ingresos=rs.getInt(1);
		}
			return ingresos;
		
		
	}
	
	public ObservableList<Producto> obtenerProductos() throws SQLException{
		ObservableList<Producto> productos = FXCollections.observableArrayList();
		
		sql="SELECT * FROM PRODUCTO";
		rs=ejecutarQuery(sql);
		while(rs.next()) {
			Producto unProducto = new Producto(rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7), rs.getInt(8));
			unProducto.setProd_id(rs.getInt(1));
			productos.add(unProducto);
		}
		return productos;
		
	}
	
	
	public List<ClientesPorBarrio> obtenerClientesPorBarrio() throws SQLException{
		List<ClientesPorBarrio> clientesPorBarrioList = new ArrayList<ClientesPorBarrio>();
		sql="SELECT dire_barrio,COUNT(dire_barrio) FROM CLIENTE "
				+ "GROUP BY dire_barrio";
		rs=ejecutarQuery(sql);
		while(rs.next()) {
			ClientesPorBarrio clientesPorBarrio = new ClientesPorBarrio(rs.getString(1), rs.getInt(2));
			clientesPorBarrioList.add(clientesPorBarrio);
		}
	
		return clientesPorBarrioList;
	}
	
	public int obtenerIdUltimaVentaIngresada() throws SQLException {
		int id = 0;
		sql="SELECT top 1 venta_id FROM VENTA ORDER BY venta_id desc";
		rs=ejecutarQuery(sql);
		while(rs.next()) {
			id=rs.getInt(1);
		}
		return id;
	}
	
	public ObservableList<Venta> obtenerVentas() throws SQLException{
		ObservableList<Venta> ventas = FXCollections.observableArrayList();
		Venta unaVenta;
		 Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
		 Statement smt= con.createStatement();
		ResultSet result;
		sql = "SELECT * FROM VENTA";
		result=stmt.executeQuery(sql);
		while(result.next()) {
			int ventaId=result.getInt(1);
			Direccion direccionAndres = new Direccion("Moliere", 1167, "Versailles", 1407);
			Cliente cliente = new Cliente(41666987, "Andrés", "Cabrera", 1553190257, "andrescabrera8199@gmail.com", direccionAndres , "CONSUMIDOR FINAL", "Instagram");
		
			LocalDate date = result.getDate(3).toLocalDate();
			
			int ventaCliente = result.getInt(2);
			//setear ganancia y precio total
			unaVenta = new Venta(cliente,date,itemsDeVenta(ventaId));
			ventas.add(unaVenta);
			}
		//obtenerUnCliente(ventaCliente)
		return ventas;
	}
	
	private Cliente obtenerUnCliente(int clie_dni) throws SQLException {
		Cliente unCliente = null;
		Direccion unaDireccion = null;
		sql = "SELECT * FROM CLIENTE "
				+ "WHERE clie_dni = '"+clie_dni+"'";
		rc=ejecutarQuery(sql);
		while(rc.next()) {
			unaDireccion = new Direccion(rc.getString(10),rc.getInt(9),rc.getString(8),rc.getInt(7));
			unCliente = new Cliente(rc.getInt(4),rc.getString(2),rc.getString(3),rc.getInt(5),rc.getString(6),unaDireccion,rc.getString(1),rc.getString(11));
		}
		rc.close();
		return unCliente;
	}

	public List<Item> itemsDeVenta(int venta_id) throws SQLException{
		System.out.println("venta id:"+venta_id);
		Producto unProducto;
		List<Item> itemsDeVenta = new ArrayList<Item>();
		Item unItem;
		sql ="SELECT * FROM ITEM_VENTA "
				+ "WHERE item_venta = '"+venta_id+"'";
		rl=ejecutarQuery(sql);
		while(rl.next()) {
			System.out.println(rl.getInt("item_producto"));
			System.out.println(rl.getInt("item_venta"));
			System.out.println(rl.getInt("item_cantidad"));
			System.out.println(rl.getInt("item_precio"));
		
		}
		rl.close();
		return itemsDeVenta;
	}

	private Producto obtenerProductoPorId(int itemProducto) throws SQLException {
		Producto unProducto = null;
		sql = "SELECT * FROM PRODUCTO "
				+ "WHERE prod_id = '"+itemProducto+"'";
		rd=ejecutarQuery(sql);
		while(rd.next()) {
			 unProducto = new Producto(rd.getString(2),rd.getDouble(3),rd.getInt(4),rd.getDouble(5),rd.getDouble(6),rd.getDouble(7), rd.getInt(8));
		}
		rd.close();
		return unProducto;
	}	

	
	
	//public List<Venta>
	
	
}
