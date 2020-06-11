package application;

import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import Alertas.Validaciones;
import Ventas.Envio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarEnvioController {

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtHorarioEntrega;

    @FXML
    private DatePicker txtFechaEntrega;

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
    		envio = new Envio(txtHorarioEntrega.getText().toString(),Double.parseDouble(txtCostoEnvio.getText()),txtFechaEntrega.getValue());
        	new Alerta().informationAlert("Se ha registrado el Envio", "Envio");
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}
    }
    
	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
    
}
