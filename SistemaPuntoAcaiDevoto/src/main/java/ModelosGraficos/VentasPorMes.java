package ModelosGraficos;

public class VentasPorMes {

	private int mes;
	private int cantidadDeVentas;
	
	public VentasPorMes(int mes, int cantidadDeVentas) {
		this.mes=mes;
		this.cantidadDeVentas=cantidadDeVentas;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getCantidadDeVentas() {
		return cantidadDeVentas;
	}

	public void setCantidadDeVentas(int cantidadDeVentas) {
		this.cantidadDeVentas = cantidadDeVentas;
	}
	
	
}
