package application;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import ConexionBD.Querys;
import ModelosClientes.Cliente;
import Productos.Producto;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NuevaVentaController implements Initializable {

    @FXML
    private TableColumn<Cliente, String> colClieDireccion;

    @FXML
    private Label lblCantidadItem;

    @FXML
    private Label lblCostoEnvio;

    @FXML
    private Button btnRealizarVenta;

    @FXML
    private Button btnNuevoCliente;
    
    @FXML
    private Button btnAgregarEnvio;

    @FXML
    private TableView<Item> tblProductosVenta;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, String> colClieApellido;

    @FXML
    private TableColumn<Item, Integer> colProdVentaCantidad;

    @FXML
    private TableColumn<Cliente, Integer> colClieDni;

    @FXML
    private TableColumn<Producto, Integer> colProdKilos;

    @FXML
    private TableColumn<Item, Double> colProdVentaPrecioTotal;

    @FXML
    private TextField txtNombreABuscar;

    @FXML
    private TableColumn<Cliente, String> colClieNombre;

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
    private TableColumn<Producto, Integer> colProdStock;
    
    @FXML
    private Button btnSacarDelCarrito;
    
    private int contadorCantidad;
    private ObservableList<Producto> productos;
    private ObservableList<Cliente> clientes;
    private ObservableList<Item> itemsAVender= FXCollections.observableArrayList();
    private double precioTotal=0;
    private VentasBuilder ventaBorrador; 

    @FXML
    void onAgregarAlCarritoClick(ActionEvent event) {
    	Producto producto = this.tblProductos.getSelectionModel().getSelectedItem();
    	
    	if(itemsAVender.stream().anyMatch(unItem -> unItem.getProducto().equals(producto))) {
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
			
			Scene scene = new Scene(root,779,340);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Nuevo Cliente");
			stage.showAndWait();
			
			ventaBorrador.setEnvio(controller.getEnvio());
			lblCostoEnvio.setText("Envio: "+ventaBorrador.getCostoEnvio()+" $");
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnNuevoClienteClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			AgregarCliente controller = loader.getController();
			controller.initAgregar();
			
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Nuevo Cliente");
			stage.showAndWait();
			
			ObtenerDatos obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientes());
			
			FilteredList<Cliente> filteredData = new FilteredList<>(clientes, p -> true);
	    	tblClientes.setItems(filteredData);
			
			txtNombreABuscar.setPromptText("Buscar...");
	    	txtNombreABuscar.textProperty().addListener((prop, old, text) -> {
	    	    filteredData.setPredicate(unCliente -> {
	    	        if(text == null || text.isEmpty()) return true;
	    	        
	    	        String name = unCliente.getNombre().toLowerCase();  
	    	        return name.contains(text.toLowerCase());
	    	    });
	    	});
			
		} catch(Exception e) {
			e.printStackTrace();
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
    void onRealizarVentaClick(ActionEvent event) {
    	Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
    	
    	if(Validaciones.validarCajaNumerica(txtPrecioTotal)) {
    		new Alerta().errorAlert("El precio Total Venta ingresado no es un valor Correcto", "Error de Datos");
    	}else {
    		if(cliente==null || itemsAVender.size() == 0) {
	    		new Alerta().errorAlert("Debe seleccionar un cliente y minimo un producto", "Nueva Venta");
	    	}else {
	    		new Alerta().informationAlert("El precio de la venta es de "+(ventaBorrador.getCostoEnvio()+Double.parseDouble(txtPrecioTotal.getText()))+" $",  "Precio Final");
	    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar la venta para "+cliente.getNombre()+" ?", "Confirmaci�n");
	        	if (action.get() == ButtonType.OK) {
		    		agregarItems(itemsAVender);
		    		ventaBorrador.setCliente(cliente);
		        	Venta nuevaVenta = ventaBorrador.crearVenta();
		        	nuevaVenta.setEnvio(ventaBorrador.getEnvio());
		        	nuevaVenta.getItems().stream().forEach(unItem -> {
						try {
							unItem.getProducto().actualizarStock(-unItem.getCantidad());
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					});
		        	
		        	double precio = 0;
		        	for (Item item : itemsAVender) {
		        		precio += item.getPrecioFinal();
		    		}
		        	double precioModificado = Double.parseDouble(txtPrecioTotal.getText());
		        	
		        	if(precio!=precioModificado) {
		        		nuevaVenta.setPrecioModificado(precioModificado);
		        	}
		        	try {
						nuevaVenta.almacenarVenta();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		        	new Alerta().informationAlert("Se ha registrado la venta", "Nueva Venta");
		        	try {
		    			FXMLLoader loader = new FXMLLoader();
		    			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
		    			AnchorPane root = (AnchorPane) loader.load();
		    			Scene scene = new Scene(root,1300,650);
		    			Stage stage = new Stage();
		    			stage.setScene(scene);
		    			stage.resizableProperty().setValue(Boolean.FALSE);
		    			stage.setResizable(false);
		    			stage.setTitle("Menu Principal");
		    			stage.show();
		    		} catch(Exception e) {
		    			e.printStackTrace();
		    		}
		        	Stage stage = (Stage) btnRealizarVenta.getScene().getWindow();
		        	stage.close();
	        	}
	    	}
    	}
	    	  	
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("�Estas seguro que desea cancelar la operaci�n?", "Confirmaci�n");
    	if (action.get() == ButtonType.OK) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Menu Principal");
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}   	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contadorCantidad = 1;
		ventaBorrador = new VentasBuilder();
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			productos = FXCollections.observableArrayList();
			productos = obtenerDatos.obtenerProductos();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		this.tblProductos.setItems(productos);	
		this.colProdNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
		this.colProdKilos.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("precioUnitario"));
		this.colProdStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
		
		try {
			obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientes());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		this.tblClientes.setItems(clientes);
		this.colClieNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		this.colClieApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
		this.colClieDni.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("dni"));
		this.colClieDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccionCompleta"));
		
		this.colProdVentaNombre.setCellValueFactory(new PropertyValueFactory<Item, String>("nombreProducto"));
		this.colProdVentaCantidad.setCellValueFactory(new PropertyValueFactory<Item, Integer>("cantidad"));
		this.colProdVentaPrecioTotal.setCellValueFactory(new PropertyValueFactory<Item, Double>("precioFinal"));
		
		FilteredList<Cliente> filteredData = new FilteredList<>(clientes, p -> true);
    	tblClientes.setItems(filteredData);
    	
    	txtNombreABuscar.setPromptText("Buscar...");
    	txtNombreABuscar.textProperty().addListener((prop, old, text) -> {
    	    filteredData.setPredicate(unCliente -> {
    	        if(text == null || text.isEmpty()) return true;
    	        
    	        String name = unCliente.getNombre().toLowerCase();  
    	        return name.contains(text.toLowerCase());
    	    });
    	});
		
		
	}
	
	public void calcularPrecioFinal(ObservableList<Item> itemsAVender) {
		precioTotal = 0;
		for (Item item : itemsAVender) {
			precioTotal += item.getPrecioFinal();
		}

		txtPrecioTotal.setText(""+precioTotal);
	}
	public void agregarItems(ObservableList<Item> itemsAVender2) {
		for (Item item : itemsAVender2) {
			ventaBorrador.setNuevoItem(item);
		}
	}


}
