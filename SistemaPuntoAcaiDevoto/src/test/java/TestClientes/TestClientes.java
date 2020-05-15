package TestClientes;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Clientes.Cliente;
import Clientes.Direccion;

public class TestClientes {
	
	
	@Test
	public void ClienteEsConsumidorFinal() {
		Direccion direccionAndres = new Direccion("Moliere", 1167, "Versailles", 1407);
		Cliente cliente = new Cliente(41666987, "Andrés", "Cabrera", 1553190257, "andrescabrera8199@gmail.com", direccionAndres , false, "Instagram");
		assertEquals(cliente.esConsumidorFinal(),true);
	}
}
