package ModelosClientes;

public class Cliente {
	
	private int dni;
	private String nombre;
	private String apellio;
	private int telefono;
	private String email;
	private Direccion direccion;
	private boolean esMayorista;
	private String comoLlego;
	
	
	public Cliente(int dni, String nombre, String apellio, int telefono, String email, Direccion direccion, boolean esMayorista, String comoLlego) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellio = apellio;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.esMayorista = esMayorista;
		this.comoLlego = comoLlego;
	}
	
	public void almacenarCliente() {
		
	}
	
	public boolean esConsumidorFinal() {
		return (!this.esMayorista);
	}
}
