package ConexionBD;

public class Querys {
	
	public String queryClientesConsumidoresFinales() {
		String variable = "consumidor final";
		return "SELECT * FROM CLIENTE WHERE clie_tipo = '"+variable+"'";
	}
	
	public String queryClientesMayoristas() {
		String variable = "mayorista";
		return "SELECT * FROM CLIENTE WHERE clie_tipo = '"+variable+"'";
	}
	
	public String queryClientes() {
		return "SELECT * FROM CLIENTE";
	}
	
	public String queryClientesDeudores() {
		return "SELECT * FROM CLIENTE WHERE clie_deuda > 0";
	}

}
