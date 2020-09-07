package application;

import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import Alertas.Validaciones;
import ModelosClientes.Direccion;
import Ventas.Envio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarEnvioController{

    @FXML
    private Button btnVolver;
    
    @FXML
    private TextField txtCalle;
    
    @FXML
    private TextField txtBarrio;

    @FXML
    private TextField txtNumero;
    
    @FXML
    private TextField txtDpto;

    @FXML
    private TextField txtHorarioEntrega;

    @FXML
    private DatePicker txtFechaEntrega;

    @FXML
    private Button btnAgregarCosto;

    @FXML
    private TextField txtCostoEnvio;
    
    @FXML
    private TextField txtObservacion;
    
    private Envio envio;

	@FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onActualizarClick(ActionEvent event) {
    	List<TextField> productosAValidar = new ArrayList<>();
    	List<TextField> productosALlenar = new ArrayList<>();
    	productosALlenar.add(txtObservacion);
    	Validaciones.ponerVaciosTextsFiels(productosALlenar);
    	productosAValidar.add(txtCostoEnvio);
    	productosAValidar.add(txtNumero);
    	if(Validaciones.validarCajasNumericas(productosAValidar)) {
    		new Alerta().errorAlert("Debe ingresar un valor numerico", "Error ingreso de Datos");
    	}else {
        	int numero = Integer.parseInt(txtNumero.getText());
        	String calle = this.txtCalle.getText().toLowerCase();
        	String barrio = this.txtBarrio.getText().toLowerCase();
        	Direccion direccion = new Direccion(calle, numero, barrio, 0);
        	if(!txtDpto.getText().isEmpty()) {
        		String dpto = txtDpto.getText();
        		direccion.setDpto(dpto);
        	}else {
        		direccion.setDpto("-1");
        	}
    		envio = new Envio(txtHorarioEntrega.getText().toString(),Double.parseDouble(txtCostoEnvio.getText()),txtFechaEntrega.getValue(),txtObservacion.getText().toString(),direccion);
        	new Alerta().informationAlert("Se ha registrado el Envio", "Envio");
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}
    }
    
    public void initEnvio(Direccion direccion) {
    	this.txtBarrio.setText(""+direccion.getBarrio());
    	this.txtCalle.setText(""+direccion.getCalle());
    	this.txtDpto.setText(""+direccion.getDpto());
    	this.txtNumero.setText(""+direccion.getNumero());
    }
    
	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

    
}
