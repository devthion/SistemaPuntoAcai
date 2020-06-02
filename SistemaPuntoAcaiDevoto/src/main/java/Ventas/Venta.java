package Ventas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ConexionBD.InsertarDatos;
import ModelosClientes.Cliente;

public class Venta {
		
	private Cliente cliente;
	private LocalDate fecha;
	private List<Item> items;
	private int venta_id;
	private double venta_precioTotal;
	private double venta_ganancia;
	InsertarDatos insertarDatos;
	private double venta_envio=0;
	
	public Venta(Cliente cliente, LocalDate fecha, List<Item> items) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.items = items;
	}
	
	public void setVenta_envio(double envio) {
		this.venta_envio=envio;
	}
	
	public double getEnvio() {
		return venta_envio;
	}
	
	public void setVenta_precioTotal(double venta_precioTotal) {
		this.venta_precioTotal=venta_precioTotal;
	}
	
	public void setVenta_ganancia(double venta_ganancia) {
		this.venta_ganancia=venta_ganancia;
	}

	public double getPrecioTotal() {
		return items.stream().mapToDouble(unItem-> unItem.getPrecioFinal()).sum() + this.venta_envio;
	}
	
	public double getCostoDeLaVenta() {
		return items.stream().mapToDouble(unItem-> unItem.getCostoTotal()).sum();
	}
	
	public double getGanancia() {
		return (getPrecioTotal()-getCostoDeLaVenta());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
	public int getMes() {
		return fecha.getMonthValue();
	}
	
	public int getAnio() {
		return fecha.getYear();
	}

	public List<Item> getItems() {
		return items;
	}
	
	public double getVenta_ganancia() {
		return this.venta_ganancia;
	}
	
	public double getVenta_precioTotal() {
		return this.venta_precioTotal;
	}

	public void almacenarVenta() throws SQLException {
		insertarDatos = new InsertarDatos();
		insertarDatos.insertarVenta(this);
	}
	
	public String getDatosCliente() {
		return cliente.getNombre()+" "+cliente.getApellido() +", DNI: "+cliente.getDni();
	}
	
}
