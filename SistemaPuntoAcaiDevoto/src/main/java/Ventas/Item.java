package Ventas;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import Productos.Producto;

public class Item {
	
	private Producto producto;
	private int cantidad;
	InsertarDatos insertarDatos;
	
	public Item(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public double getPrecioFinal() {
		//ACA HACER CUENTAS SEGUN SI ES MAYORISTA O CONSUMIDOR FINAL
		return cantidad*producto.getPrecioUnitario();
	}
	
	public String getNombreProducto() {
		return producto.getNombre()+" "+producto.getKilos() +" kilos";
	}

	public Producto getProducto() {
		return producto;
	}
	
	public int getProdId() {
		return producto.getProd_id();
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getCostoTotal() {
		return producto.getCosto()*cantidad;
	}

	public void almacenarItemVenta(int venta_id) throws SQLException {
		insertarDatos = new InsertarDatos();
		insertarDatos.insertarItemVenta(this, venta_id);
	}
}
