package ModelosClientes;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;
import ConexionBD.ObtenerDatos;

public class Cliente {
	
	private int dni;
	private String nombre;
	private String apellido;
	private int telefono;
	private String email;
	private Direccion direccion;
	private String tipo;
	private String comoLlego;

	public Cliente(int dni, String nombre, String apellido, int telefono, String email, Direccion direccion, String tipo, String comoLlego) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.tipo = tipo;
		this.comoLlego = comoLlego;
		
	}
	
	public void almacenarCliente() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarCliente(this);
	}
	
	public void modificarCliente(Cliente clienteEditado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarCliente(this.dni, clienteEditado);
	}
	
	public String getCalle() {
		return direccion.getCalle();
	}
	public int getNumero() {
		return direccion.getNumero();
	}
	public String getBarrio() {
		return direccion.getBarrio();
	}
	public int getCodPostal() {
		return direccion.getCodPostal();
	}
	
	public String getDireccionCompleta() {
		return direccion.getCalle() +" " + direccion.getNumero() +", "+direccion.getBarrio();
	}
	
	public String getTipo() {
		return tipo;
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

	public String getComoLlego() {
		return comoLlego;
	}

	public void setComoLlego(String comoLlego) {
		this.comoLlego = comoLlego;
	}
	
	public double getIngresos() throws SQLException {
		ObtenerDatos obtenerDatos = new ObtenerDatos();
		return obtenerDatos.ingresosGeneradosPor(dni);
	}

	public boolean tieneElMismoDni(int unDni) {
		// TODO Auto-generated method stub
		return this.getDni()==unDni;
	}
}
