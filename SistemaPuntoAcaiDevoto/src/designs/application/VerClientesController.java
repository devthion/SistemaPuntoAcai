package application;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerClientesController implements Initializable {
	
	@FXML
    private Button btnAgregarPersona;

    @FXML
    private TableColumn colDni;

    @FXML
    private TableColumn colApellido;

    @FXML
    private TableColumn colIngresos;

    @FXML
    private TableColumn ColTipoCliente;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn colTelefono;

    @FXML
    private TableColumn colEmail;
    
    @FXML
    private TextField txtNombre;
    
    private ObservableList<Cliente> clientes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientes = FXCollections.observableArrayList();
		
		this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
		this.colDni.setCellValueFactory(new PropertyValueFactory("dni"));
		this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
		this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
		
		
		
		
	}
	
	@FXML
    void agregarPersona(ActionEvent event) {
		/*
		Direccion direccion = new Direccion("asd", 545, "asdasd", 5456);
		Cliente cliente = new Cliente(41666987, txtNombre.getText(), "Cabrera", 41665, "asdasd", direccion, true, "Instagram");
		
		if(!this.clientes.contains(cliente)) {
			this.clientes.add(cliente);
			this.tblClientes.setItems(clientes);
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Cliente Repetido");
			alert.setTitle("Error");
			alert.setContentText("El cliente ingresado ya existe en la base de datos");
			alert.showAndWait();
		}*/
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AgregarCliente.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Nuevo Cliente");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
			
    }

}
