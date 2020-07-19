package Gastos;


public class GastosProductos extends Gasto {

	public GastosProductos(String detalle, double monto) {
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
