package TestClientes;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import Productos.Producto;
import Ventas.Item;
import Ventas.Venta;
import Ventas.VentasBuilder;

public class TestVentas {
	private Direccion direccionAndres;
	private Cliente cliente;
	private Producto producto;
	private Item item;
	private Item item2;
	private List<Item> items;
	private Venta venta;
	
	@Before
		public void init() {
		direccionAndres = new Direccion("Moliere", 1167, "Versailles", 1407);
		cliente = new Cliente(41666987, "Andrés", "Cabrera", 1553190257, "andrescabrera8199@gmail.com", direccionAndres , "CONSUMIDOR FINAL", "Instagram");
		producto = new Producto("Pote", 3, 0, 100, 200, 150, 8);
		item = new Item(producto, 5);
		item2 = new Item(new Producto("Pote", 4, 0, 50, 100, 80, 8), 3);
		items = new ArrayList<>();
		items.add(item);
		items.add(item2);
		venta = new Venta(cliente, LocalDate.now(), items);
		
	}
	@Test
	public void CostoDeLaVentaEsCostoDeLosItems() {
		assert(venta.getCostoDeLaVenta()==items.stream().mapToDouble(unItem ->unItem.getCostoTotal()).sum());
	}
	@Test
	public void GananciaDeLaVentaEsGananciaDeLosItems() {
		
		List<Venta> ventas = new ArrayList<>();
		ventas.add(venta);
		
		assert(ventas.stream().mapToDouble(unaVenta ->unaVenta.getGanancia()).sum()==(items.stream().mapToDouble(unItem ->unItem.getPrecioFinal()).sum()-items.stream().mapToDouble(unItem ->unItem.getCostoTotal()).sum()));
	}
}
