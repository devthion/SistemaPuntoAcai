package ModelosGraficos;

public class ClientesPorBarrio {
	
	String barrio;
	int cantidadClientes;
	
	public ClientesPorBarrio(String barrio, int cantidadClientes) {
		this.barrio = barrio;
		this.cantidadClientes = cantidadClientes;
	}

	public String getBarrio() {
		return barrio;
	}

	public int getCantidadClientes() {
		return cantidadClientes;
	}
	
	

}
