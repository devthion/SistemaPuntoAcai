package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import Alertas.Validaciones;
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
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
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
    	if(Validaciones.validarCajasDeTextos(generarListTxt()) || Validaciones.validarCajasNumericas(generarListNumericos()) ) {
    		new Alerta().errorAlert("Los datos ingresados son erroneos o faltan completar algunos atributos","Error en el ingreso de Datos");
    	}else {
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
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Gastos");
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}
    	
    }
    
    public Gasto generarGasto() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	
    	return new Gasto(detalle, monto);
    }
    
    public List<TextField> generarListTxt() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtMonto);
    	productosAValidar.add(txtDetalle);
    	
    	return productosAValidar;
    }
    
    public List<TextField> generarListNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtMonto);
    	
    	return productosAValidar;
    }

}
