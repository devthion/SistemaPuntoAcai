package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ConexionBD.ObtenerDatos;
import ModelosClientes.Cliente;
import Productos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private TableColumn<?, ?> colClieDireccion;

    @FXML
    private Label lblCantidadItem;

    @FXML
    private Button btnRealizarVenta;

    @FXML
    private Button btnNuevoCliente;

    @FXML
    private TableView<Producto> tblProductosVenta;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<?, ?> colClieApellido;

    @FXML
    private TableColumn<Integer, Integer> colProdVentaCantidad;

    @FXML
    private TableColumn<?, ?> colClieDni;

    @FXML
    private TableColumn<Producto, Integer> colProdKilos;

    @FXML
    private TableColumn<Producto, Double> colProdVentaPrecioTotal;

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
    private TableColumn<Producto, String> colProdVentaNombre;

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

    @FXML
    void onAgregarAlCarritoClick(ActionEvent event) {

    }

    @FXML
    void btnNuevoClienteClick(ActionEvent event) {

    }

    @FXML
    void onBuscarClienteClick(ActionEvent event) {

    }

    @FXML
    void onMenosClick(ActionEvent event) {

    }

    @FXML
    void onMasClick(ActionEvent event) {

    }

    @FXML
    void onRealizarVentaClick(ActionEvent event) {

    }

    @FXML
    void onVolverClick(ActionEvent event) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contadorCantidad = 1;
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
		this.colClieApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
		this.colClieDni.setCellValueFactory(new PropertyValueFactory("dni"));
		this.colClieDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
		
		
		
	}

}
