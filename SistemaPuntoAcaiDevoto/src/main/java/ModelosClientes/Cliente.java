package ModelosClientes;

import java.sql.SQLException;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;
import ConexionBD.ObtenerDatos;
import ModeloInversion.IngresoDiario;

public class Cliente {
	
	private int dni;
	private String nombre;
	private String apellido;
	private long telefono;
	private String email;
	private Direccion direccion;
	private String tipo;
	private String comoLlego;
	private String rubro;
	private double deuda;

	public Cliente(int dni, String nombre, String apellido, long telefono, String email, Direccion direccion, String tipo, String comoLlego, String rubro, Double deuda) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.tipo = tipo;
		this.comoLlego = comoLlego;
		this.rubro=rubro;
		this.deuda = deuda;
	}
	
	public void almacenarCliente() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.insertarCliente(this);
	}
	
	public void modificarCliente(Cliente clienteEditado) throws SQLException {
		ModificarDatos modificarDatos = new ModificarDatos();
		modificarDatos.editarCliente(this.dni, clienteEditado);
	}
	
	
	public double getDeuda() {
		return deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}
	
	public void saldarDeuda(double monto) {
		this.deuda -=monto;
		try {
			new ModificarDatos().cambiarDeuda(dni, deuda);
			IngresoDiario ingreso = new IngresoDiario("Saldo deuda de "+this.getNombre()+" "+this.getApellido(), monto);
			ingreso.almacenarIngreso();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarDeuda(double monto) {
		this.deuda +=monto;
		try {
			new ModificarDatos().cambiarDeuda(dni, deuda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRubro() {
		return rubro;
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
	
	public String getDpto() {
		return direccion.getDpto();
	}
	public int getCodPostal() {
		return direccion.getCodPostal();
	}
	
	public String getDireccionCompleta() {
		return direccion.getDireccionCompleta();
		
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

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
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
