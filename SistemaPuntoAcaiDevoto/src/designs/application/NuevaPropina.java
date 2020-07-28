package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import Propina.Propina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NuevaPropina implements Initializable{

    @FXML
    private TextField txtMonto;

    @FXML
    private Button btnVolver;

    @FXML
    private DatePicker datePropina;

    @FXML
    private Button btnNuevaPropina;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Propinas.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Propinas");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onGuardarGastoClick(ActionEvent event) {
    	if(Validaciones.validarCajasDeTextos(generarListTxt()) || Validaciones.validarCajasNumericas(generarListNumericos()) ) {
    		new Alerta().errorAlert("Los datos ingresados son erroneos o faltan completar algunos atributos","Error en el ingreso de Datos");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar la propina ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		Propina unaPropina = generarPropina();
	    		unaPropina.guardarPropina();
	    		new Alerta().informationAlert("Se ha almacenado la Propina con exito", "Nueva Propina");
	    		try {
	    			FXMLLoader loader = new FXMLLoader();
	    			loader.setLocation(getClass().getResource("Propinas.fxml"));
	    			AnchorPane root = (AnchorPane) loader.load();
	    			Scene scene = new Scene(root,1300,650);
	    			Stage stage = new Stage();
	    			stage.setScene(scene);
	    			stage.resizableProperty().setValue(Boolean.FALSE);
	    			stage.setResizable(false);
	    			stage.setTitle("Propinas");
	    			stage.show();
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	        	Stage stage = (Stage) btnVolver.getScene().getWindow();
	        	stage.close();
        	}
    	}
    }
    
    public Propina generarPropina() {
    	LocalDate fecha = this.datePropina.getValue();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	
    	return new Propina(monto, fecha);
    }
    
    public List<TextField> generarListTxt() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtMonto);
    	
    	return productosAValidar;
    }
    
    public List<TextField> generarListNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtMonto);
    	
    	return productosAValidar;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		datePropina.setValue(LocalDate.now());
	}

}
