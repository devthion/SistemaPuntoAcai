package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerProductosController implements Initializable {

    @FXML
    private Button btnActualizarStock;

    @FXML
    private Button btnAgregarNuevoProducto;

    @FXML
    private Button btnActualizarPrecio;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    void onAgregarNuevoProductoClick(ActionEvent event) {

    }

    @FXML
    void onActualizarStockClick(ActionEvent event) {

    }
    
    @FXML
    void onActualizarPrecioClick(ActionEvent event) {

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
	public void initialize(URL arg0, ResourceBundle arg1) {
		//OBTENER LOS DATOS DE LOS PRODUCTOS
		
	}

}
