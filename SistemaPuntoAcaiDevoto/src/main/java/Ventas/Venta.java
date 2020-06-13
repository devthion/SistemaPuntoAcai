package Ventas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;
import ModelosClientes.Cliente;

public class Venta {
		
	private Cliente cliente;
	private LocalDate fecha;
	private List<Item> items;
	private int venta_id;
	private double venta_precioTotal;
	private double venta_ganancia;
	InsertarDatos insertarDatos;
	//private double venta_envio=0;
	private double precioModificado=0;
	Envio unEnvio = null;
	
	public Venta(Cliente cliente, LocalDate fecha, List<Item> items) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.items = items;
	}

	public int getVenta_id() {
		return venta_id;
	}

	public String getHorario() {
		if(unEnvio==null) {
			return null;
		}else {
		return unEnvio.getHorario();}
	}
	
	public LocalDate getFechaEntrega() {
		if(unEnvio==null) {
			return LocalDate.now();
		}
		else {
		return unEnvio.getFechaEntrega();}
	}
	
	public void setEnvio(Envio unEnvio) {
		this.unEnvio=unEnvio;
	}
	
	public boolean getEstado() {
		if(unEnvio==null) {
			return true;
		}
		else {
			return unEnvio.getEstado();
		}
		
	}
	
	public double getEnvioPrecio() {
		if(unEnvio==null) {
			return 0;
		}else {
			return unEnvio.getPrecio();
		}
		
	}
	
	public void setVenta_precioTotal(double venta_precioTotal) {
		this.venta_precioTotal=venta_precioTotal;
	}
	
	public void setVenta_ganancia(double venta_ganancia) {
		this.venta_ganancia=venta_ganancia;
	}

	public double getPrecioTotal() {
		if (precioModificado == 0) {
			return items.stream().mapToDouble(unItem-> unItem.getPrecioFinal()).sum() + this.getEnvioPrecio();
		}else {
			return precioModificado + this.getEnvioPrecio();
		}
	}
	
	public double getCostoDeLaVenta() {
		return items.stream().mapToDouble(unItem-> unItem.getCostoTotal()).sum();
	}
	
	public double getGanancia() {
		return (getPrecioTotal()-getCostoDeLaVenta());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setPrecioModificado(double precioModificado) {
		this.precioModificado = precioModificado;
	}
	
	public int getMes() {
		return fecha.getMonthValue();
	}
	
	public int getAnio() {
		return fecha.getYear();
	}

	public List<Item> getItems() {
		return items;
	}
	
	public double getVenta_ganancia() {
		return this.venta_ganancia;
	}
	
	public double getVenta_precioTotal() {
		return this.venta_precioTotal;
	}

	public void almacenarVenta() throws SQLException {
		insertarDatos = new InsertarDatos();
		insertarDatos.insertarVenta(this);
	}
	
	public String getDatosCliente() {
		return cliente.getNombre()+" "+cliente.getApellido() +", DNI: "+cliente.getDni();
	}

	public void concretarVenta() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.concretarVenta(this);
	}
	
	public String getDireccionCliente() {
		return cliente.getDireccionCompleta();
	}
	
	public void cancelarVenta() throws SQLException 
	{
		this.getItems().stream().forEach(unItem -> {
			try {
				unItem.getProducto().actualizarStock(unItem.getCantidad());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		});
		new ModificarDatos().eliminarVenta(this);
	}



	public void setVentaId(int ventaId) {
		this.venta_id=ventaId;
	}
}
