package ModelosClientes;

public class Direccion {
	
	private String calle;
	private int numero;
	private String barrio;
	private int codPostal;

	public Direccion(String calle, int numero, String barrio, int codPostal) {
		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
		this.codPostal = codPostal;
	}

	public String getCalle() {
		return calle;
	}


	public int getNumero() {
		return numero;
	}


	public String getBarrio() {
		return barrio;
	}


	public int getCodPostal() {
		return codPostal;
	}

	
	
	

}
