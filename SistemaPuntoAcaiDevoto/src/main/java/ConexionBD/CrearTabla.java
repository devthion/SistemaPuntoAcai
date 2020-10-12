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
		crearTablaGastoDiario();
		crearTablaGastoGeneral();
		crearTablaGastoProducto();
		crearTablaCajaCerrada();
		crearTablaInversion();
		crearTablaPropina();
		crearTablaDispositivo();
		crearTablaEgreso();
		crearTablaIngresoDiario();
		crearTablaCombo();
	}
	
	public static void crearTablaCombo() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS COMBO" 
				+"(combo_nombre varchar(255),"
				+"combo_precio double,"
				+"PRIMARY KEY (combo_nombre))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla COMBO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public static void crearTablaDispositivo() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS DISPOSITIVO" 
				+"(dispositivo_id int,"
				+"dispositivo_valor int,"
				+"PRIMARY KEY (dispositivo_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla DISPOSITIVO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
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
	
	public static void crearTablaGastoDiario() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS GASTO_DIARIO" 
				+"(gasto_id INTEGER auto_increment,"
				+"gasto_fecha DATE,"
				+"gasto_monto DOUBLE(10),"
				+"gasto_detalle VARCHAR(255),"
				+"PRIMARY KEY (gasto_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla GASTO_DIARIO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaGastoGeneral() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS GASTO_GENERAL" 
				+"(gasto_id INTEGER auto_increment,"
				+"gasto_fecha DATE,"
				+"gasto_monto DOUBLE(10),"
				+"gasto_detalle VARCHAR(255),"
				+"PRIMARY KEY (gasto_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla GASTO_GENERAL creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaEgreso() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS EGRESO" 
				+"(egreso_id INTEGER auto_increment,"
				+"egreso_fecha DATE,"
				+"egreso_monto DOUBLE(10),"
				+"egreso_detalle VARCHAR(255),"
				+"PRIMARY KEY (egreso_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla EGRESO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaGastoProducto() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS GASTO_PRODUCTO" 
				+"(gasto_id INTEGER auto_increment,"
				+"gasto_fecha DATE,"
				+"gasto_monto DOUBLE(10),"
				+"gasto_detalle VARCHAR(255),"
				+"PRIMARY KEY (gasto_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla GASTO_PRODUCTO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaPropina() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS PROPINA" 
				+"(propina_id INTEGER auto_increment,"
				+ "propina_fecha DATE,"
				+"propina_monto DOUBLE(10),"
				+"PRIMARY KEY (propina_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla PROPINA creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void crearTablaInversion() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS INVERSION" 
				+"(inver_id INTEGER auto_increment,"
				+"inver_fecha DATE,"
				+"inver_monto DOUBLE(10),"
				+"inver_detalle VARCHAR(255),"
				+"PRIMARY KEY (inver_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla INVERSION creada");
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
				+ "venta_tipo_de_pago varchar(255),"
				+ "venta_observacion varchar(255),"
				+ "venta_envio_calle varchar(255),"
				+ "venta_envio_numero INTEGER(10),"
				+ "venta_envio_dpto varchar(40),"
				+ "venta_envio_barrio varchar(255),"
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
				+"clie_telefono BIGINT(11),"
				+"clie_email VARCHAR(40),"
				+"dire_codPostal INTEGER(5),"
				+"dire_barrio VARCHAR(40),"
				+"dire_numero INTEGER(6),"
				+"dire_calle VARCHAR(40),"
				+"clie_como_llego VARCHAR(40),"
				+"dire_dpto VARCHAR(30),"
				+"clie_rubro VARCHAR(40),"
				+"clie_deuda DOUBLE(10),"
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
	
	public static void crearTablaIngresoDiario() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"./Database/bd", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS INGRESO_DIARIO" 
				+"(ingreso_id INTEGER auto_increment,"
				+"ingreso_fecha DATE,"
				+"ingreso_monto DOUBLE(10),"
				+"ingreso_detalle VARCHAR(255),"
				+"PRIMARY KEY (ingreso_id))";
			stmt.executeUpdate(sql);
			System.out.println("Tabla INGRESO_DIARIO creada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
}
