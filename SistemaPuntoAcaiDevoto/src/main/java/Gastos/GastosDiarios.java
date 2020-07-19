package Gastos;

public class GastosDiarios extends Gasto {
	
	public GastosDiarios(String detalle, double monto) {
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
