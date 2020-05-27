package Ventas;

import Productos.Producto;

public class Item {
	
	private Producto producto;
	private int cantidad;
	
	public Item(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public double getPrecioFinal() {
		return cantidad*producto.getPrecioUnitario();
	}
	
	public String getNombreProducto() {
		return producto.getNombre()+" "+producto.getKilos() +" kilos";
	}

	public Producto getProducto() {
		return producto;
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
}
