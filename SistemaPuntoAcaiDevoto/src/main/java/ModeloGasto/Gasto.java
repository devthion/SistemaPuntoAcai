package ModeloGasto;

import java.time.LocalDate;

public class Gasto {
	private LocalDate fecha;
	private String detalle;
	private double monto;
	
	
	public Gasto(String detalle, double monto) {
		this.fecha=LocalDate.now();
		this.detalle=detalle;
		this.monto=monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public double getMonto() {
		return monto;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha=fecha;
	}
	
}
