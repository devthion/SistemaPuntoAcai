package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Alertas.Alerta;
import Alertas.Validaciones;
import Egresos.Egreso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NuevoEgreso {

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnGuardarEgreso;

    @FXML
    void onGuardarEgresoClick(ActionEvent event) throws SQLException {
    	if(Validaciones.validarCajasDeTextos(generarListTxt()) || Validaciones.validarCajasNumericas(generarListNumericos()) ) {
    		new Alerta().errorAlert("Los datos ingresados son erroneos o faltan completar algunos atributos","Error en el ingreso de Datos");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar el egreso "+txtDetalle.getText()+" ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		Egreso unEgreso = generarEgreso();
	    		unEgreso.almacenarEgreso();
	    		new Alerta().informationAlert("Se ha agregado el egreso con exito", "Nuevo Egreso");
	    		try {
	    			FXMLLoader loader = new FXMLLoader();
	    			loader.setLocation(getClass().getResource("Egresos.fxml"));
	    			AnchorPane root = (AnchorPane) loader.load();
	    			Scene scene = new Scene(root,1300,650);
	    			Stage stage = new Stage();
	    			stage.setScene(scene);
	    			stage.resizableProperty().setValue(Boolean.FALSE);
	    			stage.setResizable(false);
	    			stage.setTitle("Egresos");
	    			stage.show();
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	        	Stage stage = (Stage) btnVolver.getScene().getWindow();
	        	stage.close();
        	}
    	}
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("Egresos.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();
    		Scene scene = new Scene(root,1300,650);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.resizableProperty().setValue(Boolean.FALSE);
    		stage.setResizable(false);
    		stage.setTitle("Egresos");
    		stage.show();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

	public void initGuardarEgreso() {
		// TODO Auto-generated method stub
		
	}
	
    public Egreso generarEgreso() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	
    	return new Egreso(detalle, monto);
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
