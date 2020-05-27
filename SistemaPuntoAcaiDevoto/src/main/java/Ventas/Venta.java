package Ventas;

import java.time.LocalDate;
import java.util.ArrayList;

import ModelosClientes.Cliente;

public class Venta {
		
	private Cliente cliente;
	private LocalDate fecha;
	private ArrayList<Item> items;
	
	public Venta(Cliente cliente, LocalDate fecha, ArrayList<Item> items) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.items = items;
	}

	public double getPrecioTotal() {
		//getMovimientos().stream().filter(movimiento -> movimiento.isDeposito()).count() >= 3
		return 1;
	}
	
	public double getGanancia() {
		
		return 1;
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
	
}
