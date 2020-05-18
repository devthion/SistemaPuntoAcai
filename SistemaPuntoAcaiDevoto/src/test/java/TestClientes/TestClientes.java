package TestClientes;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;

public class TestClientes {
	
	
	@Test
	public void ClienteEsConsumidorFinal() {
		Direccion direccionAndres = new Direccion("Moliere", 1167, "Versailles", 1407);
		Cliente cliente = new Cliente(41666987, "Andrés", "Cabrera", 1553190257, "andrescabrera8199@gmail.com", direccionAndres , false, "Instagram", null);
		assertEquals(cliente.esConsumidorFinal(),true);
	}
}
