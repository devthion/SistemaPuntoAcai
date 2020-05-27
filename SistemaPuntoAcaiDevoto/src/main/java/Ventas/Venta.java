package Ventas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import ConexionBD.InsertarDatos;
import ModelosClientes.Cliente;

public class Venta {
		
	private Cliente cliente;
	private LocalDate fecha;
	private ArrayList<Item> items;
	private int venta_id;
	InsertarDatos insertarDatos;
	
	public Venta(Cliente cliente, LocalDate fecha, ArrayList<Item> items) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.items = items;
	}

	public double getPrecioTotal() {
		return items.stream().mapToDouble(unItem-> unItem.getPrecioFinal()).sum();
	}
	
	public double getCostoDeLaVenta() {
		return items.stream().mapToDouble(unItem-> unItem.getCostoTotal()).sum();
	}
	
	public double getGanancia() {
		return getPrecioTotal()-getCostoDeLaVenta();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void almacenarVenta() throws SQLException {
		insertarDatos = new InsertarDatos();
		insertarDatos.insertarVenta(this);
	}
	
}
