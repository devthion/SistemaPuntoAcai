package Ventas;

import Productos.Producto;

public class Item {
	
	private Producto producto;
	private int cantidad;

	
	public double getPrecioFinal() {
		
		return cantidad*producto.getPrecioUnitario();
	}
}
