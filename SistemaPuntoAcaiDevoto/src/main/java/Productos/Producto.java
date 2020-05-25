package Productos;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;

public class Producto {
	
	private String nombre;
	private double kilos;
	private int stock;
	private double precioUnitario;
	private double precioMayor;
	private double costo;
	private int cantidadPorMayor;
	private int cantidadVendidos;
	
	public Producto(String nombre, double kilos, int stock, double precioUnitario, double precioMayor, double costo, int cantidadPorMayor) {
		this.nombre = nombre;
		this.kilos = kilos;
		this.stock = stock;
		this.precioUnitario = precioUnitario;
		this.precioMayor = precioMayor;
		this.costo = costo;
		this.cantidadPorMayor = cantidadPorMayor;
	}
	
	public void almacenarProducto() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarProducto(this);
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCantidadPorMayor() {
		return cantidadPorMayor;
	}

	public void setCantidadPorMayor(int cantidadPorMayor) {
		this.cantidadPorMayor = cantidadPorMayor;
	}

	public String getNombre() {
		return nombre;
	}

	public double getKilos() {
		return kilos;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public double getPrecioMayor() {
		return precioMayor;
	}

	public double getCosto() {
		return costo;
	}

	public int getCantidadVendidos() {
		return cantidadVendidos;
	}
	
}
