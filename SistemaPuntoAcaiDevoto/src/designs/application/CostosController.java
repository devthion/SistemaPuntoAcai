package application;

import java.sql.SQLException;

import Alertas.Alerta;
import ModeloGasto.Gasto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CostosController {

    @FXML
    private TextField txtMonto;

    @FXML
    private Button btnNuevoGasto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Gastos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Gastos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onGuardarGastoClick(ActionEvent event) throws SQLException {
    	if(Integer.parseInt(txtMonto.getText()) != 0 ) {
    		Gasto unGasto = generarGasto();
    		unGasto.almacenarGasto();
    		new Alerta().informationAlert("Se ha agregado el Gasto con exito", "Nuevo Gasto");
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("Gastos.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.initModality(Modality.APPLICATION_MODAL);
    			stage.setTitle("Gastos");
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}else {
    		new Alerta().errorAlert("Debe ingresar un Monto", "Nuevo Gasto");
    	}
    	
    }
    
    public Gasto generarGasto() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	
    	return new Gasto(detalle, monto);
    }

}
