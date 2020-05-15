package Usuarios;

public class Usuario {
	
	private String nombre;
	private String nombreUsuario;
	private String clave;
	
	public Usuario(String nombre, String nombreUsuario, String clave) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
	}
	
	public boolean verificarClave(String contraseña) {
		return (this.clave.equals(contraseña));
	}
	
	public boolean verificarNombreUsuario(String nombreUsuario) {
		return (this.nombreUsuario.equals(nombreUsuario));
	}
	
}
