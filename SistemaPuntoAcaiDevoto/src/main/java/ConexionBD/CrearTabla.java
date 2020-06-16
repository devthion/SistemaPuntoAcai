package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CrearTabla {

	public static void main(String[]args) {
		crearTablaProducto();
		crearTablaCliente();
		crearTablaVenta();
		crearTablaItem_Venta();
		crearTablaGasto();
		crearTablaCajaCerrada();
	}
	
	public static void crearTablaCajaCerrada() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CAJACERRADA" 
				+"(caja_cerrada_fecha DATE,"
				+"caja_cerrada_monto_real DOUBLE,"
				+"caja_cerrada_monto_ideal DOUBLE,"
				+"PRIMARY KEY (caja_cerrada_fecha))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla CAJACERRADA creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaGasto() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS GASTO" 
				+"(gasto_id INTEGER auto_increment,"
				+"gasto_fecha DATE,"
				+"gasto_monto DOUBLE(10),"
				+"gasto_detalle VARCHAR(255),"
				+"PRIMARY KEY (gasto_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla GASTO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaVenta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS VENTA" 
				+"(venta_id INTEGER auto_increment,"
				+"venta_cliente INTEGER(10),"
				+"venta_fecha DATE,"
				+"venta_precioTotal DOUBLE(10),"
				+"venta_ganancia DOUBLE(10),"
				+ "venta_envio_precio DOUBLE(10) NULL,"
				+ "venta_estado_envio BOOLEAN NULL,"
				+ "venta_horario_envio varchar(255) NULL,"
				+ "venta_fecha_entrega DATE ,"
				+"PRIMARY KEY (venta_id),"
				+"FOREIGN KEY (venta_cliente) REFERENCES CLIENTE(clie_dni))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla VENTA creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaCliente() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CLIENTE" 
				+"(clie_tipo VARCHAR(40),"
				+"clie_nombre VARCHAR(40),"
				+"clie_apellido VARCHAR(40),"
				+"clie_dni INTEGER(10) NOT NULL,"
				+"clie_telefono INTEGER(10),"
				+"clie_email VARCHAR(40),"
				+"dire_codPostal INTEGER(5),"
				+"dire_barrio VARCHAR(40),"
				+"dire_numero INTEGER(6),"
				+"dire_calle VARCHAR(40),"
				+"clie_como_llego VARCHAR(40),"
				+"clie_rubro VARCHAR(40),"
				+"PRIMARY KEY (clie_dni))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla CLIENTE creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public static void crearTablaProducto() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS PRODUCTO" 
				+ "(prod_id INTEGER auto_increment,"
				+"prod_nombre VARCHAR(255),"
				+"prod_kilos DOUBLE(10),"
				+"prod_stock INTEGER(10),"
				+"prod_precio DOUBLE(10),"
				+"prod_precio_mayor DOUBLE(10),"
				+"prod_costo DOUBLE(10),"
				+"prod_cantidad_mayor int(10),"
				+"PRIMARY KEY (prod_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla PRODUCTO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaItem_Venta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ITEM_VENTA " 
				+"(item_producto INTEGER(10) NOT NULL,"
				+"item_venta INTEGER(10) NOT NULL,"
				+"item_cantidad INTEGER(10),"
				+"item_precio DOUBLE(10),"
				+"PRIMARY KEY (item_producto, item_venta),"
				+ "FOREIGN KEY (item_producto) REFERENCES PRODUCTO(prod_id),"
				+ "FOREIGN KEY (item_venta) REFERENCES VENTA(venta_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla ITEM_VENTA creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}
