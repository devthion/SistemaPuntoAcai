package application;

import java.net.URL;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private TableColumn<?, ?> colDni;

    @FXML
    private TableColumn<?, ?> colBarrio;

    @FXML
    private TableColumn<?, ?> colNumero;

    @FXML
    private TableColumn<?, ?> colIngresos;

    @FXML
    private Button btnAgregarPersona;

    @FXML
    private TableColumn<?, ?> ColTipoCliente;

    @FXML
    private TableColumn<?, ?> colNombre;

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
    private Button btnEliminarCliente;

    @FXML
    private TableColumn<?, ?> colCalle;

    @FXML
    private TableColumn<?, ?> colTelefono;
    
    private ObservableList<Cliente> clientes;

    @FXML
    void agregarPersona(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			AgregarCliente controller = loader.getController();
			controller.initAtributos(clientes);
			
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Nuevo Cliente");
			stage.showAndWait();
			
			Cliente c = controller.getCliente();
			if (c != null) {
				this.clientes.add(c);
				this.tblClientes.refresh();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void editarCliente(ActionEvent event) {
    	
    	

    }

    @FXML
    void eliminarCliente(ActionEvent event) {
    	Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
    	
    	if(cliente==null) {
    		Alerta.errorAlert("Debe seleccionar un Cliente", "Eliminar Cliente");
    	}else {
    		this.clientes.remove(cliente);
    		Alerta.informationAlert("El Cliente se ha eliminado de la Base de Datos", "Eliminar Cliente");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientes = FXCollections.observableArrayList();
		this.tblClientes.setItems(clientes);
		
		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
		this.colDni.setCellValueFactory(new PropertyValueFactory("dni"));
		this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
		this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
		
		//DATOS DE PRUEBA
		Direccion direccion = new Direccion("asd", 545, "asdasd", 5456);
    	Cliente cliente = new Cliente(54564, "adsa", "asdsad", 41, "ads", direccion, "asdasd", "WPP", 456);
    	
    	if(!this.clientes.contains(cliente)) {
			this.clientes.add(cliente);
			this.tblClientes.setItems(clientes);
		}else {
			Alerta.errorAlert("El cliente ingresado ya existe en la base de datos", "Cliente Repetido");
		}
		
		
	}

}
