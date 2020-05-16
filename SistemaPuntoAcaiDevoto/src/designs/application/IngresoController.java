package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IngresoController implements Initializable {
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void verMenuPrincipal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,900,500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


}