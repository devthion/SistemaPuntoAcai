package application;

import java.net.URL;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerClientesController implements Initializable {

    @FXML
    private TableColumn<Cliente, Integer> colDni;

    @FXML
    private TableColumn<Direccion, String> colBarrio;

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private TableColumn<?, ?> colIngresos;

    @FXML
    private Button btnAgregarPersona;

    @FXML
    private TableColumn<?, ?> ColTipoCliente;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<?, ?> colCodPostal;

    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colApellido;

    @FXML
    private Button btnEditarCliente;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<?, ?> colCalle;

    @FXML
    private TableColumn<?, ?> colTelefono;
    
    private ObservableList<Cliente> clientes;

    @FXML
    void agregarCliente(ActionEvent event) {
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
			
			this.clientes.add(controller.getNuevoCliente());
			this.tblClientes.refresh();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void editarCliente(ActionEvent event) {
    	Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			AgregarCliente controller = loader.getController();
			controller.initEditar(cliente);
			
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Editar Cliente");
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
		ObtenerDatos obtenerDatos = new ObtenerDatos();
		clientes = FXCollections.observableArrayList();
		clientes = obtenerDatos.obtenerClientes();
		
		this.tblClientes.setItems(clientes);
		
		this.colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
		this.colDni.setCellValueFactory(new PropertyValueFactory("dni"));
		this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
		this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
		this.colCalle.setCellValueFactory(new PropertyValueFactory("calle"));
		this.colNumero.setCellValueFactory(new PropertyValueFactory("numero"));
		this.colBarrio.setCellValueFactory(new PropertyValueFactory("barrio"));
		this.colCodPostal.setCellValueFactory(new PropertyValueFactory("codPostal"));
		this.ColTipoCliente.setCellValueFactory(new PropertyValueFactory("tipo"));
		this.colIngresos.setCellValueFactory(new PropertyValueFactory("ingresos"));
		
		
	}

}
