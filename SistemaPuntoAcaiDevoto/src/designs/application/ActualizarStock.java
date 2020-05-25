package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ActualizarStock implements Initializable{

    @FXML
    private Button btnMas;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblNombreProd;

    @FXML
    private Button btnMenos;

    @FXML
    private Button btnActualizar;
    
    private int contador;

    @FXML
    void onMasClick(ActionEvent event) {
    	contador++;
    	lblStock.setText(""+contador);
    }

    @FXML
    void onMenosClick(ActionEvent event) {
    	contador--;
    	lblStock.setText(""+contador);
    }

    @FXML
    void onActualizarClick(ActionEvent event) {
    	
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contador = 0;
		lblStock.setText(""+contador);
	}
	
	public void initActualizar(String nombreProd) {
		lblNombreProd.setText(nombreProd);
	}

}
