package ModelosClientes;

import java.util.Objects;

public class Direccion {
	
	private String calle;
	private int numero;
	private String barrio;
	private int codPostal;
	private String dpto = null;
	
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
	
	public String getDpto() {
		return dpto;
	}
	
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	
	public String getDireccionCompleta() {
		if(Objects.isNull(dpto) || dpto.equalsIgnoreCase("null") || dpto == null) {
			return calle +" " + numero +", "+barrio;
		}else {
			return calle +" " + numero +", Depto "+dpto+", "+barrio;
		}
	}

	
	
	

}
