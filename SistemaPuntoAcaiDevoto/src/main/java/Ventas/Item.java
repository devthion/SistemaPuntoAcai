package Ventas;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import Productos.Producto;

public class Item {
	
	private Producto producto;
	private int cantidad;
	private double item_precio;
	private int item_venta;
	InsertarDatos insertarDatos;
	private boolean siempreMayorista = false;
	
	public Item(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	

	public int getItem_venta() {
		return item_venta;
	}



	public void setItem_venta(int item_venta) {
		this.item_venta = item_venta;
	}

	public double getPrecioUnitarioProducto() {
		return producto.getPrecioUnitario();
	}
	
	public double getPrecioMayoristaProducto() {
		return producto.getPrecioMayor();
	}


	public boolean isSiempreMayorista() {
		return siempreMayorista;
	}



	public void setSiempreMayorista(boolean siempreMayorista) {
		this.siempreMayorista = siempreMayorista;
	}



	public double getPrecioFinal() {
		//ACA HACER CUENTAS SEGUN SI ES MAYORISTA O CONSUMIDOR FINAL
		if(esCompraMayorista() || this.siempreMayorista) {
			return cantidad*this.getPrecioMayoristaProducto();
		}else {
			return cantidad*this.getPrecioUnitarioProducto();
		}
	}
	
	public boolean esCompraMayorista() {
		return cantidad>= producto.getCantidadPorMayor();
	}
	
	public String getNombreProducto() {
		return producto.getNombre()+" "+producto.getKilos() +" cc";
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
