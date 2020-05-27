package application;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ModelosClientes.Cliente;
import Productos.Producto;
import Ventas.Item;
import Ventas.Venta;
import Ventas.VentasBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NuevaVentaController implements Initializable {

    @FXML
    private TableColumn<Cliente, String> colClieDireccion;

    @FXML
    private Label lblCantidadItem;

    @FXML
    private Button btnRealizarVenta;

    @FXML
    private Button btnNuevoCliente;

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
    
    private int contadorCantidad;
    private ObservableList<Producto> productos;
    private ObservableList<Cliente> clientes;
    private ObservableList<Item> itemsAVender= FXCollections.observableArrayList();
    private double precioTotal=0;
    private VentasBuilder ventaBorrador; 

    @FXML
    void onAgregarAlCarritoClick(ActionEvent event) {
    	Producto producto = this.tblProductos.getSelectionModel().getSelectedItem();
    	
    	
    	if(producto==null) {
    		Alerta.errorAlert("Debe seleccionar un Producto", "Actualizar Stock");
    	}else {
    		Item item = new Item(producto, contadorCantidad);
    		contadorCantidad = 1;
    		lblCantidadItem.setText(""+contadorCantidad);
    		itemsAVender.add(item);
    		tblProductosVenta.setItems(itemsAVender);
    		this.tblProductosVenta.refresh();
    		
    		ventaBorrador.setNuevoItem(item);
    		precioTotal += item.getPrecioFinal();
    		
    		txtPrecioTotal.setText(""+precioTotal);
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
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Nuevo Cliente");
			stage.showAndWait();
			
			ObtenerDatos obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			clientes = obtenerDatos.obtenerClientes();
			
			this.tblClientes.setItems(clientes);
			this.tblClientes.refresh();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onBuscarClienteClick(ActionEvent event) {
    	//String nombreABuscarString = txtNombreABuscar.getText();
    	//buscarEnClien
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
    	//ALMACENA LA VENTA
    	//RECIBE UNA LISTA DE ITEMS VENTA Y UN CLIENTE Y GENERA LA VENTA
    	Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
    	
    	if(cliente==null || ventaBorrador.getItems().size() == 0) {
    		Alerta.errorAlert("Debe seleccionar un cliente y minimo un producto", "Nueva Venta");
    	}else {
    		ventaBorrador.setCliente(cliente);
        	Venta nuevaVenta = ventaBorrador.crearVenta();
        	try {
				nuevaVenta.almacenarVenta();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	Alerta.informationAlert("Se ha registrado la venta", "Nueva Venta");
        	try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.initModality(Modality.APPLICATION_MODAL);
    			stage.setTitle("Menu Principal");
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	Stage stage = (Stage) btnRealizarVenta.getScene().getWindow();
        	stage.close();
    	}  	
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	
    	
    	Optional<ButtonType> action = Alerta.preguntaConfirmacion("¿Estas seguro que desea cancelar la operación?", "Confirmación");
    	if (action.get() == ButtonType.OK) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.initModality(Modality.APPLICATION_MODAL);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.tblProductos.setItems(productos);	
		this.colProdNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		this.colProdKilos.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("kilos"));
		this.colProdStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
		
		try {
			obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			clientes = obtenerDatos.obtenerClientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
	}

}
