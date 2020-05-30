package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerClientesMayoristas {

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
    private TableView<?> tblClientes;

    @FXML
    private TableColumn<?, ?> colCodPostal;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private Button btnEditarCliente;

    @FXML
    private TableColumn<?, ?> colApellido;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<?, ?> colCalle;

    @FXML
    private TableColumn<?, ?> colTelefono;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerClientes.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Clientes");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void editarCliente(ActionEvent event) {

    }

    @FXML
    void agregarCliente(ActionEvent event) {

    }

}
