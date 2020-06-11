package application;

import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import Alertas.Validaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarEnvioController {

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnAgregarCosto;

    @FXML
    private TextField txtCostoEnvio;
    
    private Envio envio;

	@FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onActualizarClick(ActionEvent event) {
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtCostoEnvio);
    	if(Validaciones.validarCajasNumericas(productosAValidar)) {
    		new Alerta().errorAlert("Debe ingresar un valor numerico", "Error ingreso de Datos");
    	}else {
    		costoEnvio = Double.parseDouble(txtCostoEnvio.getText());
        	new Alerta().informationAlert("Se ha agregado el costo de envio con exito", "Envio");
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}
    
    }
    
    public double getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

}
