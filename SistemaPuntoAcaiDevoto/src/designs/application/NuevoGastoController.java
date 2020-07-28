package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Alertas.Alerta;
import Alertas.Validaciones;
import Gastos.GastosGenerales;
import Gastos.GastosProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NuevoGastoController {

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnGuardarGastoGeneral;

    @FXML
    private Button btnGuardarGastoProductos;

    @FXML
    private Button btnGuardarGastoDiario;

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onGuardarGastoGeneralClick(ActionEvent event) throws SQLException {
    	if(Validaciones.validarCajasDeTextos(generarListTxt()) || Validaciones.validarCajasNumericas(generarListNumericos()) ) {
    		new Alerta().errorAlert("Los datos ingresados son erroneos o faltan completar algunos atributos","Error en el ingreso de Datos");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar el gasto "+txtDetalle.getText()+" ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		GastosGenerales unGasto = new GastosGenerales(this.txtDetalle.getText().toString(), Double.parseDouble(this.txtMonto.getText().toString()));
	    		unGasto.almacenarGasto();
	    		new Alerta().informationAlert("Se ha almacenado el Gasto con exito", "Nuevo Gasto");
	        	Stage stage = (Stage) btnVolver.getScene().getWindow();
	        	stage.close();
        	}
    	}
    }

    @FXML
    void onGuardarGastoDiarioCick(ActionEvent event) {
    	
    }

    @FXML
    void onGuardarGastoProductosClick(ActionEvent event) throws SQLException {
    	if(Validaciones.validarCajasDeTextos(generarListTxt()) || Validaciones.validarCajasNumericas(generarListNumericos()) ) {
    		new Alerta().errorAlert("Los datos ingresados son erroneos o faltan completar algunos atributos","Error en el ingreso de Datos");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar el gasto "+txtDetalle.getText()+" ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		GastosProductos unGasto = new GastosProductos(this.txtDetalle.getText().toString(), Double.parseDouble(this.txtMonto.getText().toString()));
	    		unGasto.almacenarGasto();
	    		new Alerta().informationAlert("Se ha almacenado el Gasto con exito", "Nuevo Gasto");
	        	Stage stage = (Stage) btnVolver.getScene().getWindow();
	        	stage.close();
        	}
    	}
    }
    
    public void initGuardarProductos() {    	
    	btnGuardarGastoDiario.setVisible(false);
    	btnGuardarGastoGeneral.setVisible(false);
    	btnGuardarGastoProductos.setVisible(true);
    }
    
    public void initGuardarDiarios() {
    	btnGuardarGastoDiario.setVisible(true);
    	btnGuardarGastoGeneral.setVisible(false);
    	btnGuardarGastoProductos.setVisible(false);	
    }
    
    public void initGuardarGenerales() {
    	btnGuardarGastoDiario.setVisible(false);
    	btnGuardarGastoGeneral.setVisible(true);
    	btnGuardarGastoProductos.setVisible(false);
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
