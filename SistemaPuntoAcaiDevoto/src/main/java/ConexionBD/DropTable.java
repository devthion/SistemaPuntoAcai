package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DropTable {
	
	public static void main(String[]args) {
		dropTablaItem_Venta();
		dropTablaProducto();
		dropTablaVenta();
		dropTablaCliente();
		dropTablaGasto();
	}
	
	public static void dropTablaGasto() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "DROP TABLE GASTO";
			stmt.executeUpdate(sql);
			System.out.println("Tabla GASTO eliminada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public static void dropTablaVenta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "DROP TABLE VENTA";
			stmt.executeUpdate(sql);
			System.out.println("Tabla VENTA eliminada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public static void dropTablaCliente() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "DROP TABLE CLIENTE";
			stmt.executeUpdate(sql);
			System.out.println("Tabla CLIENTE eliminada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public static void dropTablaProducto() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "DROP TABLE PRODUCTO";
			stmt.executeUpdate(sql);
			System.out.println("Tabla PRODUCTO eliminada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public static void dropTablaItem_Venta() {
		try 
		{
			Connection con = DriverManager.getConnection("jdbc:h2:"+"~/test", "root", "devthion");
			Statement stmt= con.createStatement();
			String sql = "DROP TABLE ITEM_VENTA";
			stmt.executeUpdate(sql);
			System.out.println("Tabla ITEM_VENTA eliminada");
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
