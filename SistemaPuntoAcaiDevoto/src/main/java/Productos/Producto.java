package Productos;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;
import ConexionBD.ObtenerDatos;

public class Producto {
	
	private String nombre;
	private double kilos;
	private int stock;
	private double precioUnitario;
	private double precioMayor;
	private double costo;
	private int cantidadPorMayor;
	private int cantidadVendidos;
	private int prod_id;
	ModificarDatos modificarDatos;
	ObtenerDatos obtenerDatos;
	
	public Producto(String nombre, double kilos, int stock, double precioUnitario, double precioMayor, double costo, int cantidadPorMayor) {
		this.nombre = nombre;
		this.kilos = kilos;
		this.stock = stock;
		this.precioUnitario = precioUnitario;
		this.precioMayor = precioMayor;
		this.costo = costo;
		this.cantidadPorMayor = cantidadPorMayor;
	}
	
	public int getCantidadTotalVendidos() throws SQLException {
		
		obtenerDatos = new ObtenerDatos();
		return obtenerDatos.obtenerCantidadTotalVendida(this.prod_id);
	}
	
	
	public void almacenarProducto() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarProducto(this);
	}
	
	public void actualizarStock(int unaCantidad) throws SQLException {
		modificarDatos = new ModificarDatos();
		modificarDatos.actualizarStock(prod_id, stock+unaCantidad);
	}
	
	public void actualizarPrecios(double precioUnitario, double precioMayor, double costo) throws SQLException {
		modificarDatos = new ModificarDatos();
		modificarDatos.actualizarPrecios(precioUnitario, precioMayor, costo, prod_id);
	}
	
	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
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
	
	public String getNombreProducto() {
		return this.getNombre()+" "+this.getKilos() +" cc";
	}

	public int getCantidadVendidos() {
		//TIENE QUE RETORNAR LA CONSULTA
		return cantidadVendidos;
	}
	
}
