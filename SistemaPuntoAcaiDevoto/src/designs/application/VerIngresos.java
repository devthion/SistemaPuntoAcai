package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerIngresos {

    @FXML
    private Label lblIdealMes;

    @FXML
    private Label lblRealAnio;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblIdelaDia;

    @FXML
    private Label lblRealDia;

    @FXML
    private TextField txtMes;

    @FXML
    private TextField txtAnio;

    @FXML
    private Label lblRealMes;

    @FXML
    private Label lblIdealAnio;

    @FXML
    private TextField txtDia;
    
    @FXML
    private Button btnVerIngresos;

    @FXML
    void onVolverClick(ActionEvent event) {
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
    

    @FXML
    void onVerIngresosClick(ActionEvent event) {
    	
    }

}
