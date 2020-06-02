package ModelosGraficos;

public class ComoLlegoUnCliente {
	private int cantidadClientes;
	private String comoLlego;
	
	public ComoLlegoUnCliente(String comoLlego, int cantidadClientes) {
		this.cantidadClientes=cantidadClientes;
		this.comoLlego=comoLlego;
	}
	
	public int getCantidadClientes() {
		return cantidadClientes;
	}
	
	public String getComoLlego() {
		return comoLlego;
	}

}
