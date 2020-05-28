package Ventas;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import Productos.Producto;

public class Item {
	
	private Producto producto;
	private int cantidad;
	private double item_precio;
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
	
	public void setItemPrecio(double precio) {
		this.item_precio=precio;
	}
	
	public double getItemPrecio() {
		return item_precio;
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
