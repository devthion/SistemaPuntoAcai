package Ventas;

import java.time.LocalDate;
import java.util.ArrayList;

import ModelosClientes.Cliente;

public class VentasBuilder {
	
	private Cliente cliente;
	private LocalDate fecha;
	private ArrayList<Item> items = new ArrayList<>();
	
	public VentasBuilder() {}
	
	public Venta crearVenta() {
		return new Venta(cliente, LocalDate.now(), items);
	}
	
	public void setNuevoItem(Item item) {
		items.add(item);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	
	
	
}
