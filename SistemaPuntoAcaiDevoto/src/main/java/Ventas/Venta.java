package Ventas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ConexionBD.InsertarDatos;
import ConexionBD.ModificarDatos;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;

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
	private Envio unEnvio = null;
	private String tipoDePago;
	

	public Venta(Cliente cliente, LocalDate fecha, List<Item> items, String tipoDePago) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.items = items;
		this.tipoDePago = tipoDePago;
	}

	public int getVenta_id() {
		return venta_id;
	}

	public String getTipoDePago() {
		return tipoDePago;
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
			return false;
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
	
	public String getObservacion() {
		if(unEnvio==null) {
			return "";
		}else {
			return unEnvio.getObservacion();
		}
	}
	
	public void setVenta_precioTotal(double venta_precioTotal) { //ESTE ES EL PRECIO QUE SE OBTIENE DE LA BASE DE DATOS, EL CUAL SE SETEA, POR QUE SI LO CALCULARAMOS
																// NOS DARIA OTRA COSA SI CAMBIARAMOS EL PRECIO DE UN PRODUCTO
		this.venta_precioTotal=venta_precioTotal;
	}
	
	public void setVenta_ganancia(double venta_ganancia) {
		this.venta_ganancia=venta_ganancia;
	}

	public double getPrecioTotal() {
		return precioModificado + this.getEnvioPrecio(); //ESTO ES PARA ALMACENAR LA VENTA NO PARA TRAERLA Y CONSULTAR POR EL PRECIO TOTAL
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
	
	public String getNombreCliente() {
		return this.cliente.getNombre();
	}
	
	public String getClienteContacto() {
		if(Long.toString(this.cliente.getTelefono()).isEmpty()) {
			if(this.cliente.getEmail().isEmpty()) {
				return "sin contacto";
			}else {
				return this.cliente.getEmail();
			}
		}else {
			return Long.toString(this.cliente.getTelefono());
		}
		
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
		return this.venta_precioTotal; //CON ESTE OBTENGO EL PRECIO TOTAL, ALMACENADO EN LA BD.
	}

	public void almacenarVenta() throws SQLException {
		insertarDatos = new InsertarDatos();
		insertarDatos.insertarVenta(this);
	}
	
	public String getDatosCliente() {
		return cliente.getNombre()+" "+cliente.getApellido();
	}

	public void concretarVenta() throws SQLException {
		InsertarDatos insertarDatos = new InsertarDatos();
		insertarDatos.concretarVenta(this);
	}
	
	public String getDireccionCliente() {
		return unEnvio.getDireccion().getDireccionCompleta();
	}
	public String getDireccionMayorista() {
		return unEnvio.getDireccion().getDireccionMayorista();
	}
	
	
	public void cancelarVenta() throws SQLException 
	{
		this.getItems().stream().forEach(unItem -> {
			try {
				System.out.println("CANTIDAD: "+ unItem.getCantidad());
				unItem.getProducto().actualizarStock(unItem.getCantidad());
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		});
		new ModificarDatos().eliminarVenta(this);
	}
	
	public Envio getUnEnvio() {
		return unEnvio;
	}
	
	public Double getPrecioEnvio() {
		if(unEnvio !=null) {
			return unEnvio.getPrecio();
		}else {
			return 0.0;
		}
	}
	
	public void setVentaId(int ventaId) {
		this.venta_id=ventaId;
	}
}
