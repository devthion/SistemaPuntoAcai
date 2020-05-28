package application;

import java.util.Optional;

import Alertas.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngresoController {
	
    @FXML
    private TextField etClave;

    @FXML
    private TextField etNombreUsuario;

    @FXML
    private Label txtNombreUsuario;

    @FXML
    private Button btnIngresar;

    @FXML
    private Label txtClave;

    @FXML
    void onIngresarClick(ActionEvent event) {
    	System.out.println("Se precionio el boton ingresar"+etNombreUsuario.getText());
    	
    	verMenuPrincipal();
    	Stage stage = (Stage) btnIngresar.getScene().getWindow();
    	stage.close();
    	
    }
	
	public void verMenuPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @FXML
    void onSalirClick(ActionEvent event) {
    	Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea salir del sistema?", "Confirmación");
    	if (action.get() == ButtonType.OK) {
    			System.exit(0);
    	}
    }
}