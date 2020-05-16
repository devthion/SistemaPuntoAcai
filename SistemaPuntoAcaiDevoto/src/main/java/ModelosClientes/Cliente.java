package ModelosClientes;

public class Cliente {
	
	private int dni;
	private String nombre;
	private String apellido;
	private int telefono;
	private String email;
	private Direccion direccion;
	private boolean esMayorista;
	private String comoLlego;
	
	
	public Cliente(int dni, String nombre, String apellido, int telefono, String email, Direccion direccion, boolean esMayorista, String comoLlego) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
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
	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellio) {
		this.apellido = apellio;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public boolean isEsMayorista() {
		return esMayorista;
	}

	public void setEsMayorista(boolean esMayorista) {
		this.esMayorista = esMayorista;
	}

	public String getComoLlego() {
		return comoLlego;
	}

	public void setComoLlego(String comoLlego) {
		this.comoLlego = comoLlego;
	}
}
