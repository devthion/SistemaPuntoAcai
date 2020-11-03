package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import ConexionBD.Querys;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import Productos.Combo;
import Productos.Producto;
import Ventas.Envio;
import Ventas.Item;
import Ventas.Venta;
import Ventas.VentasBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditarVenta implements Initializable{

	@FXML
	private Label lblCantidadItem;

	@FXML
	private Button btnRealizarVenta;

	@FXML
	private TextField txtDescuento;

	@FXML
	private TableView<Item> tblProductosVenta;

	@FXML
	private Button btnSacarDelCarrito;

	@FXML
	private MenuButton menuTipoPago;

	@FXML
	private TableColumn<Combo, Double> colComboPrecio;

	@FXML
	private TableView<Combo> tblCombo;

	@FXML
	private Button btnAgregarEnvio;

	@FXML
	private Label lblCostoEnvio;

	@FXML
	private Button btnAgregarCombo;

	@FXML
	private TableColumn<Item, Integer> colProdVentaCantidad;

	@FXML
	private TableColumn<Item, Double> colProdVentaPrecioTotal;

	@FXML
	private TableColumn<Producto, Integer> colProdKilos;

	@FXML
	private TableView<Producto> tblProductos;

	@FXML
	private Button btnMas;

	@FXML
	private Button btnVolver;

	@FXML
	private TableColumn<Producto, String> colProdNombre;

	@FXML
	private TableColumn<Item, String> colProdVentaNombre;

	@FXML
	private Button btnAgregarAlCarrito;

	@FXML
	private Button btnMenos;

	@FXML
	private TextField txtPrecioTotal;

	@FXML
	private TableColumn<Combo, String> colComboNombre;

	@FXML
	private TableColumn<Producto, Integer> colProdStock;

	private Double costoEnvio=0.0;

	private int contadorCantidad;
	private ObservableList<Producto> productos;
	private ObservableList<Cliente> clientes;
	private ObservableList<Combo> combos;
	private ObservableList<Item> itemsAVender = FXCollections.observableArrayList();
	private ArrayList<Item> itemsVendidos = new ArrayList<Item>();
	private double precioTotal=0;

	private VentasBuilder ventaBorrador;
	private Venta ventaEliminar;
	
	ObtenerDatos obtenerDatos;
	
	public void initVenta(Venta ventaEditable) throws SQLException {
		ventaBorrador = new VentasBuilder();
		ventaBorrador.setCliente(ventaEditable.getCliente());
		ventaBorrador.setEnvio(ventaEditable.getUnEnvio());
		lblCostoEnvio.setText("Envio: "+ventaBorrador.getEnvio().getPrecio()+" $");
		for (int i = 0; i < ventaEditable.getItems().size(); i++) {
			itemsAVender.add(new Item(ventaEditable.getItems().get(i).getProducto(), ventaEditable.getItems().get(i).getCantidad()));
		}
		itemsAVender.stream().forEach(unitem -> System.out.println(unitem.getNombreProducto()));
		calcularPrecioFinal(itemsAVender);
		tblProductosVenta.setItems(FXCollections.observableArrayList(itemsAVender));
		ventaBorrador.setFecha(ventaEditable.getFecha());
		ventaBorrador.setTipoDePago(ventaEditable.getTipoDePago());
		menuTipoPago.setText(ventaEditable.getTipoDePago());
		ventaEliminar = ventaEditable;
		ventaEliminar.cancelarVenta();
		obtenerDatos = new ObtenerDatos();
		productos = FXCollections.observableArrayList();
		productos = obtenerDatos.obtenerProductos();
		ObservableList<Producto> productosSinRegalos = FXCollections.observableArrayList();
		productosSinRegalos = productos.filtered(unProducto -> !unProducto.getNombre().contains("regalo"));
		this.tblProductos.setItems(productosSinRegalos);
	}

	@FXML
	void onRealizarVentaClick(ActionEvent event) throws SQLException {
		if(txtDescuento.getText().isEmpty()) {
			txtDescuento.setText(""+0);
		}
		if(Validaciones.validarCajaNumerica(txtPrecioTotal) || Validaciones.validarCajaNumerica(txtDescuento) ) {
			new Alerta().errorAlert("El precio Total Venta o el Descuento ingresado no es un valor Correcto", "Error de Datos");
		}else {
			if(itemsAVender.size() == 0) {
				new Alerta().errorAlert("Debe seleccionar minimo un producto", "Nueva Venta");
			}else {
				if(menuTipoPago.getText().equalsIgnoreCase("Tipo de Pago")) {
					new Alerta().errorAlert("Debe seleccionar un tipo de Pago", "Nueva Venta");
				}else {
					new Alerta().informationAlert("El precio de la venta es de "+(aplicarDescuento(Double.parseDouble(txtPrecioTotal.getText())+ costoEnvio))+" $",  "Precio Final");
					Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar la venta para "+ventaBorrador.getCliente().getNombre()+" ?", "Confirmación");
					if (action.get() == ButtonType.OK) {
						ventaBorrador.setTipoDePago(menuTipoPago.getText().toString());
						agregarItems(itemsAVender);
						Venta nuevaVenta = ventaBorrador.crearVenta();
						nuevaVenta.setEnvio(ventaBorrador.getEnvio());
						
						nuevaVenta.getItems().stream().forEach(unItem -> {
							try {
								unItem.getProducto().actualizarStock(-unItem.getCantidad());
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
						});
					


						double precioModificado = Double.parseDouble(txtPrecioTotal.getText());

						nuevaVenta.setPrecioModificado(aplicarDescuento(precioModificado));
						nuevaVenta.getUnEnvio().setPrecio(nuevaVenta.getEnvioPrecio());
						try {
							nuevaVenta.almacenarVenta();
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						System.out.println(nuevaVenta.getTipoDePago());
						new Alerta().informationAlert("Se ha registrado la venta", "Nueva Venta");
						Stage stage = (Stage) btnRealizarVenta.getScene().getWindow();
						stage.close();
					}
				}        		
			}
		}
	}


	@FXML
	void onVolverClick(ActionEvent event) {
		new Alerta().errorAlert("Debe continuar con la venta", "Cancelar Edicion");
	}

	@FXML
	void onSacarDelCarritoClick(ActionEvent event) {
		Item item = this.tblProductosVenta.getSelectionModel().getSelectedItem();
		if(item==null) {
			new Alerta().errorAlert("Debe seleccionar un Producto del Carrito", "Agregar al Carrito");
		}else {
			itemsAVender.remove(item);
			tblProductosVenta.setItems(itemsAVender);
			this.tblProductosVenta.refresh();

			calcularPrecioFinal(itemsAVender);
		}
	}

	@FXML
	void onAgregarEnvioClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarEnvio.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			AgregarEnvioController controller = loader.getController();
			controller.initEnvio(ventaBorrador.getCliente().getDireccion());

			Scene scene = new Scene(root,1059,507);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Agregar Envio");
			stage.showAndWait();

			if(Objects.isNull(controller.getEnvio())) {
				costoEnvio=0.0;
				lblCostoEnvio.setText("Envio: "+costoEnvio+" $");
				ventaBorrador.getEnvio().setDireccion(ventaBorrador.getCliente().getDireccion());
			}else {
				ventaBorrador.setEnvio(controller.getEnvio());
				lblCostoEnvio.setText("Envio: "+ventaBorrador.getEnvio().getPrecio()+" $");
				costoEnvio=ventaBorrador.getEnvio().getPrecio();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void calcularPrecioFinal(ObservableList<Item> itemsAVender) {
		precioTotal = 0;
		for (Item item : itemsAVender) {
			if(ventaBorrador.getCliente().getTipo().equalsIgnoreCase("mayorista")) {
				precioTotal += item.getPrecioMayoristaProducto() * item.getCantidad();
			}else {
				precioTotal += item.getPrecioFinal();
			}
		}
		if(this.tblCombo.getSelectionModel().getSelectedItem() == null) {
			txtPrecioTotal.setText(""+precioTotal);
		}

	}

	public void agregarItems(ObservableList<Item> itemsAVender2) {
		for (Item item : itemsAVender2) {
			ventaBorrador.setNuevoItem(item);
		}
	}

	@FXML
	void onAgregarAlCarritoClick(ActionEvent event) {
		Producto producto = this.tblProductos.getSelectionModel().getSelectedItem();

		if(itemsAVender.stream().anyMatch(unItem -> unItem.getProducto().getNombre().equals(producto.getNombre()) && 
				unItem.getProducto().getKilos() == producto.getKilos()  )) {
			new Alerta().errorAlert("El producto solicitado ya existe en el carrito", "Error de duplicidad");
		}else {
			if(producto==null) {
				new Alerta().errorAlert("Debe seleccionar un Producto", "Agregar al Carrito");
			}else {
				Item item = new Item(producto, contadorCantidad);
				contadorCantidad = 1;
				lblCantidadItem.setText(""+contadorCantidad);
				itemsAVender.add(item);
				tblProductosVenta.setItems(itemsAVender);
				this.tblProductosVenta.refresh();

				calcularPrecioFinal(itemsAVender);
			}
		}
	}

	@FXML
	void onMenosClick(ActionEvent event) {
		if(contadorCantidad==1) {
			lblCantidadItem.setText(""+contadorCantidad);
		}else {
			contadorCantidad--;
			lblCantidadItem.setText(""+contadorCantidad);
		}
	}

	@FXML
	void onMasClick(ActionEvent event) {
		contadorCantidad++;
		lblCantidadItem.setText(""+contadorCantidad);
	}

	@FXML
	void onEfectivoClick(ActionEvent event) {
		menuTipoPago.setText("Efectivo");
	}

	@FXML
	void onTransferenciaClick(ActionEvent event) {
		menuTipoPago.setText("Transferencia Bancaria");
	}

	@FXML
	void onMercadoPagoClick(ActionEvent event) {
		menuTipoPago.setText("Mercado Pago");
	}

	public Double aplicarDescuento(double valor) {
		if(txtDescuento.getText().isEmpty()) {
			return valor;
		}else {
			return valor - (Double.parseDouble(txtDescuento.getText()) * valor / 100);
		}
	}

	@FXML
	void onAgregarComboClick(ActionEvent event) {
		Combo combo = this.tblCombo.getSelectionModel().getSelectedItem();

		if(combo==null) {
			new Alerta().errorAlert("Debe seleccionar un combo", "Nueva Venta");
		}else {
			txtPrecioTotal.setText(""+combo.getCombo_precio());
		}
	}

	@FXML
	void onSinRegaloClick(ActionEvent event) {
		ObservableList<Producto> productosSinRegalos = FXCollections.observableArrayList();
		productosSinRegalos = productos.filtered(unProducto -> !unProducto.getNombre().contains("regalo"));
		this.tblProductos.setItems(productosSinRegalos);
	}

	@FXML
	void onTodosClick(ActionEvent event) {
		this.tblProductos.setItems(productos);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contadorCantidad = 1;
		ventaBorrador = new VentasBuilder();
		try {
			obtenerDatos = new ObtenerDatos();
			productos = FXCollections.observableArrayList();
			productos = obtenerDatos.obtenerProductos();
			combos = FXCollections.observableArrayList();
			combos = obtenerDatos.obtenerCombos();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ObservableList<Producto> productosSinRegalos = FXCollections.observableArrayList();
		productosSinRegalos = productos.filtered(unProducto -> !unProducto.getNombre().contains("regalo"));
		this.tblProductos.setItems(productosSinRegalos);

		this.colProdNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
		this.colProdKilos.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("precioUnitario"));
		this.colProdStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));

		this.tblCombo.setItems(combos);

		this.colComboNombre.setCellValueFactory(new PropertyValueFactory<Combo, String>("Combo_nombre"));
		this.colComboPrecio.setCellValueFactory(new PropertyValueFactory<Combo, Double>("Combo_precio"));

		try {
			obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientes());

		} catch (SQLException e) {

			e.printStackTrace();
		}


		this.colProdVentaNombre.setCellValueFactory(new PropertyValueFactory<Item, String>("nombreProducto"));
		this.colProdVentaCantidad.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cantidad"));
		this.colProdVentaPrecioTotal.setCellValueFactory(new PropertyValueFactory<Item, Double>("precioFinal"));

		//cambia estoo.
		ventaBorrador.setEnvio(new Envio("", 0.0, LocalDate.now(), "", new Direccion("", 0, "", "")));

	}

}
