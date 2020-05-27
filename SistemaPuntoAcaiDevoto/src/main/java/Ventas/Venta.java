package Ventas;

import java.time.LocalDate;

import ModelosClientes.Cliente;

public class Venta {
		
	private Cliente cliente;
	private LocalDate fecha;
	private Item items;
	
	public Venta(Cliente cliente, LocalDate fecha, Item items) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.items = items;
	}

	public double getPrecioTotal() {
		
		return 1;
	}
	
	public double getGanancia() {
		
		return 1;
	}
	
}
