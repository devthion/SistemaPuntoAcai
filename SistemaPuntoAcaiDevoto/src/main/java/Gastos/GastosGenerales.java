package Gastos;

public class GastosGenerales extends Gasto {
	
	public GastosGenerales(String detalle, double monto) {
		super(detalle, monto);
	}
	
	public void almacenarGasto() {
		//ALMACENAR GASTO
	}
	
	public void editarGasto(Gasto gastoModificado) {
		//ModificarDatos.modificarGasto(this, gastoModificado);
	}
	
	public void eliminarGasto() {
		//ModificarDatos.eliminarGasto(this);
	}
}
